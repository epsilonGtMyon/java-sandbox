package epsilongtmyon.app.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
		return DriverManager.getConnection(url);
	}
}
