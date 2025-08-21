package epsilongtmyon.sandbox.sandbox04;

import java.math.BigDecimal;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import epsilongtmyon.common.db.DbInitializer;
import epsilongtmyon.common.db.SqlSessionFactoryLoader;
import epsilongtmyon.common.db.entity.MyException;
import epsilongtmyon.common.db.mapper.MyExceptionMapper;
import epsilongtmyon.sandbox.sandbox02.plugins.CommonFieldInterceptor;
import epsilongtmyon.sandbox.sandbox02.plugins.LoggingInterceptor1;

// 例外はorg.apache.ibatis.exceptions.PersistenceException で統一されていて
// h2の例外が  にラップされている。
// なので自分で原因を調べる必要がある。
public class Sandbox04Main {

	private static final Logger logger = LoggerFactory.getLogger(Sandbox04Main.class);

	private final SqlSession sqlSession;

	private final MyExceptionMapper myExceptionMapper;

	public Sandbox04Main(
			SqlSession sqlSession,
			MyExceptionMapper myExceptionMapper) {
		super();
		this.sqlSession = sqlSession;
		this.myExceptionMapper = myExceptionMapper;
	}

	public static void main(String[] args) {

		SqlSessionFactory sqlSessionFactory = newSqlSessionFactory();
		try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
			epsilongtmyon.common.db.DbInitializer dbIni = new DbInitializer(sqlSession);
			dbIni.initializer("init-db.sql");
			sqlSession.commit();

			MyExceptionMapper myExceptionMapper = sqlSession.getMapper(MyExceptionMapper.class);

			Sandbox04Main main = new Sandbox04Main(sqlSession, myExceptionMapper);
			main.doNotNull();
			main.doDuplicateKey();
			main.doInvalidScale();

			sqlSession.commit();
		}
	}

	private static SqlSessionFactory newSqlSessionFactory() {

		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryLoader.load();
		// xml以外からも登録
		Configuration configuration = sqlSessionFactory.getConfiguration();

		// --
		configuration.addInterceptor(new LoggingInterceptor1());
		configuration.addInterceptor(new CommonFieldInterceptor());

		return sqlSessionFactory;
	}

	private void doNotNull() {
		logger.info("doNotNull");
		try {
			MyException keyNull = new MyException();
			myExceptionMapper.insert(keyNull);
		} catch (Exception e) {
			logger.error("例外", e);
		}
	}

	private void doDuplicateKey() {
		logger.info("doDuplicateKey");
		try {
			MyException duplicateKey = new MyException();
			duplicateKey.setExKey("EX001");
			myExceptionMapper.insert(duplicateKey);
		} catch (Exception e) {
			logger.error("例外", e);
		}
	}

	private void doInvalidScale() {
		logger.info("doInvalidScale");
		try {
			MyException invalidScale = new MyException();
			invalidScale.setExKey("EX002");
			invalidScale.setAmount(new BigDecimal("12345678901.123"));
			myExceptionMapper.insert(invalidScale);
		} catch (Exception e) {
			logger.error("例外", e);
		}
	}

}
