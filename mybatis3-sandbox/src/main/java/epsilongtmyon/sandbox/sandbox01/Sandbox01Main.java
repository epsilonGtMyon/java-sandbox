package epsilongtmyon.sandbox.sandbox01;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import epsilongtmyon.common.db.DbInitializer;
import epsilongtmyon.common.db.SqlSessionFactoryLoader;
import epsilongtmyon.common.db.entity.MyLog;
import epsilongtmyon.common.db.entity.MyTable;
import epsilongtmyon.sandbox.sandbox01.annotation.MyLogAnnotationMapper;
import epsilongtmyon.sandbox.sandbox01.annotation.MyTableAnnotationMapper;
import epsilongtmyon.sandbox.sandbox01.xml.MyLogXmlMapper;
import epsilongtmyon.sandbox.sandbox01.xml.MyTableXmlMapper;

public class Sandbox01Main {

	private final SqlSession sqlSession;

	public Sandbox01Main(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public static void main(String[] args) {

		SqlSessionFactory sqlSessionFactory = newSqlSessionFactory();
		try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
			epsilongtmyon.common.db.DbInitializer dbIni = new DbInitializer(sqlSession);
			dbIni.initializer("init-db.sql");
			sqlSession.commit();

			Sandbox01Main main = new Sandbox01Main(sqlSession);

			main.doXml01();
			main.doXmlInsertNull();
			
			main.doAnnotation01();
			main.doAnnotationInsertNull();

			sqlSession.commit();
		}
	}

	private static SqlSessionFactory newSqlSessionFactory() {

		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryLoader
				.load("epsilongtmyon/sandbox/sandbox01/mybatis-config.xml");
		// xml以外からも登録
		Configuration configuration = sqlSessionFactory.getConfiguration();
		configuration.addMapper(MyLogAnnotationMapper.class);
		configuration.addMapper(MyTableAnnotationMapper.class);
		return sqlSessionFactory;
	}

	// 基本操作
	private void doXml01() {

		MyLogXmlMapper myLogMapper = sqlSession.getMapper(MyLogXmlMapper.class);
		MyTableXmlMapper myTableMapper = sqlSession.getMapper(MyTableXmlMapper.class);
		// ----
		MyTable myTable01 = myTableMapper.findByKey(new BigInteger("1"));
		System.out.println(myTable01);

		// ----
		List<MyTable> myTables = myTableMapper.findAll();
		myTables.stream().forEach(System.out::println);

		// ----
		MyLog myLog01 = new MyLog();
		myLog01.setLogMessage("あいうえお");
		myLogMapper.insert(myLog01);

		// 自動生成のキーが代入されている。
		System.out.println(myLog01);

	}
	
	private void doXmlInsertNull() {
		// nullを入れるときにはsql側に, jdbcType=VARCHAR などを記載する必要がある。

		MyLogXmlMapper myLogMapper = sqlSession.getMapper(MyLogXmlMapper.class);
		MyLog myLog = new MyLog();
		myLogMapper.insert(myLog);
		
	}

	// アノテーションで操作
	private void doAnnotation01() {

		MyLogAnnotationMapper myLogMapper = sqlSession.getMapper(MyLogAnnotationMapper.class);
		MyTableAnnotationMapper myTableMapper = sqlSession.getMapper(MyTableAnnotationMapper.class);
		// ----
		MyTable myTable01 = myTableMapper.findByKey(new BigInteger("1"));
		System.out.println(myTable01);

		// ----
		List<MyTable> myTables = myTableMapper.findAll();
		myTables.stream().forEach(System.out::println);
		// ----
		MyLog myLog01 = new MyLog();
		myLog01.setLogMessage("あいうえお");
		myLogMapper.insert(myLog01);

		// 自動生成のキーが代入されている。
		System.out.println(myLog01);

	}

	
	private void doAnnotationInsertNull() {
		// nullを入れるときにはsql側に, jdbcType=VARCHAR などを記載する必要がある。

		MyLogAnnotationMapper myLogMapper = sqlSession.getMapper(MyLogAnnotationMapper.class);
		MyLog myLog = new MyLog();
		myLogMapper.insert(myLog);
		
	}
}
