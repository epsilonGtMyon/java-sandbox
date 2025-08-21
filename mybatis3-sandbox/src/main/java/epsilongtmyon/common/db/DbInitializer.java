package epsilongtmyon.common.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

	private final SqlSession sqlSession;

	public DbInitializer(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public void initializer(String sqlResource) {

		Connection con = sqlSession.getConnection();

		try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(sqlResource);
				InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(isr)) {

			StringBuilder sqlBuf = new StringBuilder();
			String line = null;

			while ((line = br.readLine()) != null) {
				line = line.stripTrailing();
				if (line.isEmpty() ||
						line.stripLeading().startsWith("--")) {
					continue;
				}

				sqlBuf.append(line).append(System.lineSeparator());

				if (line.endsWith(";")) {
					String sql = sqlBuf.substring(0, sqlBuf.lastIndexOf(";"));
					sqlBuf = new StringBuilder();

					try (Statement stmt = con.createStatement()) {
						logger.info(sql);
						stmt.execute(sql);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}

				}

			}

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}
}
