package epsilongtmyon.sandbox.sandbox02;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import epsilongtmyon.common.db.DbInitializer;
import epsilongtmyon.common.db.SqlSessionFactoryLoader;
import epsilongtmyon.common.db.entity.MyLog;
import epsilongtmyon.common.db.mapper.MyLogMapper;
import epsilongtmyon.sandbox.sandbox02.plugins.CommonFieldInterceptor;
import epsilongtmyon.sandbox.sandbox02.plugins.LoggingInterceptor1;

public class Sandbox02Main {

	private static final Logger logger = LoggerFactory.getLogger(Sandbox02Main.class);

	private final SqlSession sqlSession;

	public Sandbox02Main(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public static void main(String[] args) {

		SqlSessionFactory sqlSessionFactory = newSqlSessionFactory();
		try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
			epsilongtmyon.common.db.DbInitializer dbIni = new DbInitializer(sqlSession);
			dbIni.initializer("init-db.sql");
			sqlSession.commit();

			Sandbox02Main main = new Sandbox02Main(sqlSession);
			main.start01();

			sqlSession.commit();
		}
	}

	private static SqlSessionFactory newSqlSessionFactory() {

		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryLoader.load();
		// xml以外からも登録
		Configuration configuration = sqlSessionFactory.getConfiguration();
		// ここ以下のインターフェースを全部登録
		configuration.addMappers("epsilongtmyon.common.db.mapper");

		Collection<Class<?>> mappers = configuration.getMapperRegistry().getMappers();
		logger.info("mappers = {}", mappers);

		// --
		configuration.addInterceptor(new LoggingInterceptor1());
		configuration.addInterceptor(new CommonFieldInterceptor());

		return sqlSessionFactory;
	}

	// インターセプターの確認をするために いくつかのオペレーションを実行
	private void start01() {
		logger.info("start01 begin");

		MyLogMapper myLogMapper = sqlSession.getMapper(MyLogMapper.class);

		logger.info("insert");

		for (int i = 1; i <= 10; i++) {
			MyLog myLog = new MyLog();
			myLog.setLogMessage("ログメッセージ" + i);
			myLogMapper.insert(myLog);
		}

		MyLog myLog2 = myLogMapper.select(new BigInteger("2"));
		myLog2.setLogMessage("更新ログ");
		myLogMapper.update(myLog2);

		myLogMapper.deleteByKey(new BigInteger("3"));

		MyLog myLog4 = myLogMapper.select(new BigInteger("4"));
		myLogMapper.delete(myLog4);

		List<MyLog> myLogs = myLogMapper.selectAll();
		myLogs.forEach(System.out::println);

		logger.info("start01 end");
	}

}
