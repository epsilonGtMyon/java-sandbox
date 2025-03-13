package epsilongtmyon.service.sample.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import epsilongtmyon.service.sample.db.entity.TestTable02;

public class TestTable02Dao {

	public void insert(Connection con, TestTable02 entity) throws SQLException {

		String sql = """
				insert into TEST_TABLE02 (
				   KEY1
				  ,KEY2
				  ,VARCHAR_COL
				  ,NUMBER_COL
				  ,TIMESTAMP_COL
				) values (
				   ?
				  ,?
				  ,?
				  ,?
				  ,?
				)
				""";

		try (PreparedStatement pStmt = con.prepareStatement(sql)) {

			pStmt.setString(1, entity.getKey1());
			pStmt.setString(2, entity.getKey2());
			pStmt.setString(3, entity.getVarcharCol());
			pStmt.setBigDecimal(4, entity.getNumberCol());
			pStmt.setTimestamp(5, entity.getTimestampCol());

			pStmt.executeUpdate();
		}

	}
}
