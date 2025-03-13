package epsilongtmyon.support.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import epsilongtmyon.support.util.ResourceUtil;

public class DatabasaeInitializer {
	
	private static final Logger logger = LoggerFactory.getLogger(DatabasaeInitializer.class);

	public void initializer(Connection con) throws SQLException {

		try (Statement stmt = con.createStatement()) {
			logger.info("データベースを初期化します。");
			
			// テストテーブル作成
			String schemaSql = ResourceUtil.getResourceAsString("schema.sql");
			for (String sql : schemaSql.split(":")) {
				logger.info("schema SQL={}", sql);
				stmt.execute(sql);
			}
			String dataSql = ResourceUtil.getResourceAsString("data.sql");
			for (String sql : dataSql.split(":")) {
				logger.info("data SQL={}", sql);
				stmt.execute(sql);
			}
		}
	}
}
