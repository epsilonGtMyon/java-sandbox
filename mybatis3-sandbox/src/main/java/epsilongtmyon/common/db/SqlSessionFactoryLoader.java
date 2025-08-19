package epsilongtmyon.common.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryLoader {

	public static SqlSessionFactory load() {
		return load("mybatis-config.xml");
	}

	public static SqlSessionFactory load(String resource) {
		try (InputStream is = Resources.getResourceAsStream(resource)) {
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			return sqlSessionFactory;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
