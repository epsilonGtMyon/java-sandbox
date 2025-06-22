package epsilongtmyon.config.doma;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.seasar.doma.slf4j.Slf4jJdbcLogger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MyDbConfig implements Config {

	private static final MyDbConfig CONFIG = MyDbConfig.of();

	private final JdbcLogger jdbcLogger;

	private final Dialect dialect;

	private final LocalTransactionDataSource dataSource;

	private final TransactionManager transactionManager;

	public MyDbConfig(
			JdbcLogger jdbcLogger,
			Dialect dialect,
			LocalTransactionDataSource dataSource,
			TransactionManager transactionManager) {
		super();
		this.jdbcLogger = jdbcLogger;
		this.dialect = dialect;
		this.dataSource = dataSource;
		this.transactionManager = transactionManager;
	}

	public static MyDbConfig of() {
		var jdbcLogger = new Slf4jJdbcLogger();
		var dialect = new H2Dialect();
		var ltds = new LocalTransactionDataSource(createDataSource());
		var txManager = new LocalTransactionManager(ltds.getLocalTransaction(jdbcLogger));
		return new MyDbConfig(
				jdbcLogger,
				dialect,
				ltds,
				txManager);
	}

	private static DataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:h2:tcp://localhost/test");
		config.setUsername("sa");
		config.setPassword("");
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}

	public static MyDbConfig singleton() {
		return CONFIG;
	}

	@Override
	public JdbcLogger getJdbcLogger() {
		return jdbcLogger;
	}

	@Override
	public Dialect getDialect() {
		return dialect;
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public LocalTransaction getLocalTransaction() {
		return dataSource.getLocalTransaction(getJdbcLogger());
	}

}
