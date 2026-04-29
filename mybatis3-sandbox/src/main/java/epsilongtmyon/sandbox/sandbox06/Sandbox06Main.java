package epsilongtmyon.sandbox.sandbox06;

import java.util.Collection;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import epsilongtmyon.common.db.DbInitializer;
import epsilongtmyon.common.db.SqlSessionFactoryLoader;
import epsilongtmyon.common.db.entity.MyLog;
import epsilongtmyon.common.db.mapper.MyLogMapper;

// JavaコードでSqlSessionFactoryを作る
// https://mybatis.org/mybatis-3/java-api.html#sqlsessions

public class Sandbox06Main {

	private static final Logger logger = LoggerFactory.getLogger(SqlSessionFactoryLoader.class);

	private final MyLogMapper myLogMapper;

	public Sandbox06Main(MyLogMapper myLogMapper) {
		this.myLogMapper = myLogMapper;
	}

	public static void main(String[] args) {

		SqlSessionFactory sqlSessionFactory = newSqlSessionFactory();
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			epsilongtmyon.common.db.DbInitializer dbIni = new DbInitializer(sqlSession);
			dbIni.initialize("init-db.sql");
			sqlSession.commit();

			MyLogMapper myLogMapper = sqlSession.getMapper(MyLogMapper.class);

			Sandbox06Main main = new Sandbox06Main(myLogMapper);
			main.start01();

			sqlSession.commit();
		}
	}

	private static SqlSessionFactory newSqlSessionFactory() {

		// Configurationの作成するための準備

		// DataSourceはMyBatisのPOOLEDの時に使ってるやつをここでは使う
		DataSource dataSource = new PooledDataSource(
				"org.h2.Driver", "jdbc:h2:tcp://localhost/test-mybatis", "sa", "");
		TransactionFactory transactionFactory = new JdbcTransactionFactory();

		Environment environment = new Environment("development", transactionFactory, dataSource);

		// Configurationの作成
		Configuration configuration = new Configuration(environment);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setLogImpl(Slf4jImpl.class);

		// ここ以下のインターフェースを全部登録
		configuration.addMappers("epsilongtmyon.common.db.mapper");

		Collection<Class<?>> mappers = configuration.getMapperRegistry().getMappers();
		logger.info("mappers = {}", mappers);

		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(configuration);

		return factory;

	}

	private void start01() {
		MyLog myLog = new MyLog();
		myLog.setLogMessage("ログメッセージ");
		myLogMapper.insert(myLog);
	}

}
