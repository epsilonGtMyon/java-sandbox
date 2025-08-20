package epsilongtmyon.common.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Collection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlSessionFactoryLoader {
	
	private static final Logger logger = LoggerFactory.getLogger(SqlSessionFactoryLoader.class);

	public static SqlSessionFactory load() {
		return load("mybatis-config.xml");
	}

	public static SqlSessionFactory load(String resource) {
		try (InputStream is = Resources.getResourceAsStream(resource)) {
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			
			Configuration configuration = sqlSessionFactory.getConfiguration();
			// ここ以下のインターフェースを全部登録
			configuration.addMappers("epsilongtmyon.common.db.mapper");

			Collection<Class<?>> mappers = configuration.getMapperRegistry().getMappers();
			logger.info("mappers = {}", mappers);
			
			return sqlSessionFactory;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
