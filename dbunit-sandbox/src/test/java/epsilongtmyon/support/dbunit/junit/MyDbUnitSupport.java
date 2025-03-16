package epsilongtmyon.support.dbunit.junit;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import epsilongtmyon.support.dbunit.DatabaseTesterProvider;

public class MyDbUnitSupport implements AutoCloseable {

	private static final String STORE_KEY = MyDbUnitSupport.class + "_storeKey";

	private final IDatabaseTester dbTester;

	private IDatabaseConnection dbConnection;

	private MyDbUnitSupport(IDatabaseTester dbTester) {
		super();
		this.dbTester = dbTester;
	}

	// ----
	// getter

	public IDatabaseTester getDbTester() {
		return dbTester;
	}

	public IDatabaseConnection getDbConnection() throws Exception {
		if (dbConnection == null) {
			dbConnection = this.dbTester.getConnection();
		}
		return dbConnection;
	}

	// ----

	@Override
	public void close() throws Exception {

		try {
			dbTester.onTearDown();
		} catch (Exception ignore) {
		}

		if (dbConnection != null) {
			dbConnection.close();
		}
	}

	public static MyDbUnitSupport fromStore(Store store) {
		return store.getOrComputeIfAbsent(STORE_KEY, k -> {

			try {
				IDatabaseTester dbTester = DatabaseTesterProvider.get().getDatabaseTester();
				return new MyDbUnitSupport(dbTester);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}, MyDbUnitSupport.class);
	}

	public static void removeStore(Store store) {

		MyDbUnitSupport support = store.remove(STORE_KEY, MyDbUnitSupport.class);
		if (support == null) {
			return;
		}

		try {
			support.close();
		} catch (Exception ignore) {
		}
	}
}
