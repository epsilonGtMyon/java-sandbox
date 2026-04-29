package epsilongtmyon.sandbox.sandbox07;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import epsilongtmyon.common.db.DbInitializer;
import epsilongtmyon.common.db.SqlSessionFactoryLoader;
import epsilongtmyon.common.db.entity.MyTable;

// returnInstanceForEmptyRow の確認
// returnInstanceForEmptyRowはtrueにしておいたほうがよさそう
public class Sandbox07Main {

	private static final Logger logger = LoggerFactory.getLogger(Sandbox07Main.class);

	private final Sandbox07Mapper mapper;

	public Sandbox07Main(Sandbox07Mapper mapper) {
		super();
		this.mapper = mapper;
	}

	public static void main(String[] args) {

		// データの準備
		try (SqlSession sqlSession = newSqlSessionFactory(true).openSession()) {
			epsilongtmyon.common.db.DbInitializer dbIni = new DbInitializer(sqlSession);
			dbIni.initialize("init-db.sql");
			dbIni.initialize("epsilongtmyon/sandbox/sandbox07/data.sql");
			sqlSession.commit();
		}

		// returnInstanceForEmptyRow: true
		try (SqlSession sqlSession = newSqlSessionFactory(true).openSession()) {
			Sandbox07Mapper mapper = sqlSession.getMapper(Sandbox07Mapper.class);
			Sandbox07Main main = new Sandbox07Main(mapper);
			logger.info("returnInstanceForEmptyRow: trueで実行");
			main.start01();
			sqlSession.commit();
		}

		// returnInstanceForEmptyRow: false
		try (SqlSession sqlSession = newSqlSessionFactory(false).openSession()) {
			Sandbox07Mapper mapper = sqlSession.getMapper(Sandbox07Mapper.class);
			Sandbox07Main main = new Sandbox07Main(mapper);
			logger.info("returnInstanceForEmptyRow: falseで実行");
			main.start01();
			sqlSession.commit();
		}
	}

	private static SqlSessionFactory newSqlSessionFactory(boolean returnInstanceForEmptyRow) {

		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryLoader.load();
		// xml以外からも登録
		Configuration configuration = sqlSessionFactory.getConfiguration();
		configuration.setReturnInstanceForEmptyRow(returnInstanceForEmptyRow);
		configuration.addMapper(Sandbox07Mapper.class);

		return sqlSessionFactory;
	}

	private void start01() {
		
		List<MyTable> results = mapper.selectNull();
		//returnInstanceForEmptyRow = false のときはMyTableのインスタンスが生成されずnullになっている。
		results.forEach(r -> logger.info(Objects.toString(r)));

	}
}
