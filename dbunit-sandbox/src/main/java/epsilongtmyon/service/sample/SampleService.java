package epsilongtmyon.service.sample;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import epsilongtmyon.service.sample.db.dao.TestTable02Dao;
import epsilongtmyon.service.sample.db.dao.TestTableDao;
import epsilongtmyon.service.sample.db.entity.TestTable;
import epsilongtmyon.service.sample.db.entity.TestTable02;

public class SampleService {

	private TestTableDao testTableDao = new TestTableDao();

	private TestTable02Dao testTable02Dao = new TestTable02Dao();

	public void execute() throws Exception {

		try (Connection con = getConnection()) {

			List<TestTable> testTables = testTableDao.findAll(con);

			for (TestTable testTable : testTables) {

				TestTable02 t2 = new TestTable02();
				t2.setKey1(testTable.getKey1());
				t2.setKey2(testTable.getKey2());
				t2.setVarcharCol(testTable.getVarcharCol());
				BigDecimal b = testTable.getNumberCol();
				if (b != null) {
					t2.setNumberCol(b.add(BigDecimal.ONE));
				}
				t2.setTimestampCol(testTable.getTimestampCol());
				testTable02Dao.insert(con, t2);
			}

			TestTable02 x1 = new TestTable02();
			x1.setKey1("XXXX1");
			x1.setKey2("XXXX2");
			x1.setVarcharCol(null);
			x1.setNumberCol(new BigDecimal("11111.11"));
			x1.setTimestampCol(Timestamp.valueOf("2025-03-01 11:00:00"));
			testTable02Dao.insert(con, x1);
		}

	}

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/dbtest",
				"sa",
				"");

	}
}
