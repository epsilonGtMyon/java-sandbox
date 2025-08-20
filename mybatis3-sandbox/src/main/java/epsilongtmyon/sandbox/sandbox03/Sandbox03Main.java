package epsilongtmyon.sandbox.sandbox03;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigInteger;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import epsilongtmyon.common.db.DbInitializer;
import epsilongtmyon.common.db.SqlSessionFactoryLoader;
import epsilongtmyon.common.db.entity.Series;
import epsilongtmyon.common.db.mapper.SeriesMapper;
import epsilongtmyon.sandbox.sandbox02.plugins.CommonFieldInterceptor;
import epsilongtmyon.sandbox.sandbox02.plugins.LoggingInterceptor1;

public class Sandbox03Main {
	private static final Logger logger = LoggerFactory.getLogger(Sandbox03Main.class);

	private final SqlSession sqlSession;

	public Sandbox03Main(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public static void main(String[] args) {

		SqlSessionFactory sqlSessionFactory = newSqlSessionFactory();
		try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
			epsilongtmyon.common.db.DbInitializer dbIni = new DbInitializer(sqlSession);
			dbIni.initializer("init-db.sql");
			sqlSession.commit();

			Sandbox03Main main = new Sandbox03Main(sqlSession);
			main.start01();

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

	// インターセプターの確認をするために いくつかのオペレーションを実行
	private void start01() {
		SeriesMapper seriesMapper = sqlSession.getMapper(SeriesMapper.class);

		// 逐次フェッチ
		try (Cursor<Series> cursor = seriesMapper.select(new BigInteger("100"))) {

			for (Series s : cursor) {

				logger.info("{}:{}", cursor.getCurrentIndex(), s);
			}

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}
}
