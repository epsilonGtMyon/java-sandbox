package epsilongtmyon.support.props;

import java.util.Properties;

import epsilongtmyon.support.util.ResourceUtil;

public class TestProperties {

	private static final TestProperties instance = new TestProperties();

	private final Db db;

	private TestProperties() {

		Properties props = ResourceUtil.getResourceAsProperties("test.properties");

		this.db = new Db(
				props.getProperty("db.driverClassName"),
				props.getProperty("db.url"),
				props.getProperty("db.user"),
				props.getProperty("db.password"),
				props.getProperty("db.schema"));

	}

	public static TestProperties getInstance() {
		return instance;
	}

	public Db getDb() {
		return db;
	}

	public static class Db {

		private final String driverClassName;

		private final String url;

		private final String user;

		private final String password;

		private final String schema;

		public Db(
				String driverClassName,
				String url,
				String user,
				String password,
				String schema

		) {
			super();
			this.driverClassName = driverClassName;
			this.url = url;
			this.user = user;
			this.password = password;
			this.schema = schema;
		}

		public String getDriverClassName() {
			return driverClassName;
		}

		public String getUrl() {
			return url;
		}

		public String getUser() {
			return user;
		}

		public String getPassword() {
			return password;
		}

		public String getSchema() {
			return schema;
		}

	}
}
