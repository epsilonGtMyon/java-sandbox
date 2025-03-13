package epsilongtmyon.support.dbunit;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;

import epsilongtmyon.support.props.TestProperties;
import epsilongtmyon.support.props.TestProperties.Db;

public class DatabaseTesterProvider {

	public static DatabaseTesterProvider get() {
		return new DatabaseTesterProvider();
	}

	public IDatabaseTester getDatabaseTester() throws ClassNotFoundException {
		Db db = TestProperties.getInstance().getDb();

		IDatabaseTester dbTester = new JdbcDatabaseTester(
				db.getDriverClassName(),
				db.getUrl(),
				db.getUser(),
				db.getPassword(),
				db.getSchema()) {

			@Override
			public IDatabaseConnection getConnection() throws Exception {
				IDatabaseConnection conn = super.getConnection();

				DatabaseConfig config = conn.getConfig();
				// 空文字列の登録を許可する。
				config.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);

				return conn;
			}
		};
		return dbTester;
	}
}
