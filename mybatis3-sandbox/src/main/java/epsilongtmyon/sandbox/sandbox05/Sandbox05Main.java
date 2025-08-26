package epsilongtmyon.sandbox.sandbox05;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import epsilongtmyon.common.db.DbInitializer;
import epsilongtmyon.common.db.SqlSessionFactoryLoader;
import epsilongtmyon.common.db.entity.MyRawsql;

// 生SQLの実行
// https://qiita.com/kumazo/items/72ecdb2923b77aaa0c94

public class Sandbox05Main {

	private static final Logger logger = LoggerFactory.getLogger(Sandbox05Main.class);

	private final SqlSession sqlSession;

	private final Sandbox05Mapper mapper;

	public Sandbox05Main(
			SqlSession sqlSession, Sandbox05Mapper mapper) {
		super();
		this.sqlSession = sqlSession;
		this.mapper = mapper;
	}

	public static void main(String[] args) {

		SqlSessionFactory sqlSessionFactory = newSqlSessionFactory();
		try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
			epsilongtmyon.common.db.DbInitializer dbIni = new DbInitializer(sqlSession);
			dbIni.initializer("init-db.sql");
			sqlSession.commit();

			Sandbox05Mapper mapper = sqlSession.getMapper(Sandbox05Mapper.class);

			Sandbox05Main main = new Sandbox05Main(sqlSession, mapper);
//			main.start01();
			main.start02();
			sqlSession.commit();
		}
	}

	private static SqlSessionFactory newSqlSessionFactory() {

		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryLoader.load();
		// xml以外からも登録
		Configuration configuration = sqlSessionFactory.getConfiguration();
		configuration.addMapper(Sandbox05Mapper.class);

		return sqlSessionFactory;
	}

	private void start01() {
		logger.info("-------------start01");

		String sql = """
				select
				  *
				from
				  MY_RAWSQL
				order by
				  ID
				""";
		
		var param = new HashMap<String, Object>();
		List<MyRawsql> results = mapper.selectRaw(sql, param);

		results.forEach(System.out::println);
	}
	


	private void start02() {
		logger.info("-------------start01");

		String sql = """
				select
				  *
				from
				  MY_RAWSQL
				where
				  COL1 like #{param.col1} || '%'
				and COL2 > #{param.col2}
				order by
				  ID
				""";
		
		var param = new HashMap<String, Object>();
		param.put("col1", "値1");
		param.put("col2", 15);
		
		List<MyRawsql> results = mapper.selectRaw(sql, param);

		results.forEach(System.out::println);
	}

}
