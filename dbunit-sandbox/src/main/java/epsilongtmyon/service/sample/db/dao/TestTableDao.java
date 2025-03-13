package epsilongtmyon.service.sample.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import epsilongtmyon.service.sample.db.entity.TestTable;

public class TestTableDao {

	public List<TestTable> findAll(Connection con) throws SQLException {

		String sql = """
				select
				   KEY1
				  ,KEY2
				  ,VARCHAR_COL
				  ,NUMBER_COL
				  ,TIMESTAMP_COL
				from
				   TEST_TABLE
				order by
				   KEY1
				  ,KEY2
				""";

		List<TestTable> records = new ArrayList<>();
		try (Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				TestTable t = new TestTable();

				t.setKey1(rs.getString("KEY1"));
				t.setKey2(rs.getString("KEY2"));
				t.setVarcharCol(rs.getString("VARCHAR_COL"));
				t.setNumberCol(rs.getBigDecimal("NUMBER_COL"));
				t.setTimestampCol(rs.getTimestamp("TIMESTAMP_COL"));

				records.add(t);
			}

		}
		return records;

	}
}
