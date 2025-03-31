package epsilongtmyon.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// DB接続をともなうダミーテスト
public class DummyTest {

	@Test
	@DisplayName("H2データベースに接続してselect 1")
	void select1() throws Exception {

		try (Connection con = getConnection();
				Statement stmt = con.createStatement()) {

			try (ResultSet rs = stmt.executeQuery("select 1 as val");) {
				while (rs.next()) {
					System.out.println(rs.getString(1));
				}
			}
		}
	}

	@Test
	@DisplayName("H2データベースに接続してテーブルにクエリ発行")
	void selectTestTable() throws Exception {

		try (Connection con = getConnection();
				Statement stmt = con.createStatement()) {

			try (ResultSet rs = stmt.executeQuery("select KEY01, COL01 from TEST_TABLE order by KEY01");) {
				while (rs.next()) {
					System.out.println("--");
					System.out.println(rs.getString("KEY01"));
					System.out.println(rs.getString("COL01"));
				}
			}
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:h2:tcp://localhost/testDb","sa", "");
	}
}
