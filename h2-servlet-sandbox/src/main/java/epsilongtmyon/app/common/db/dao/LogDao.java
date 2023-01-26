package epsilongtmyon.app.common.db.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import epsilongtmyon.app.common.db.entity.Log;

public class LogDao {


	public void insert(Connection con, Log log) throws SQLException {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.println("insert into LOG (");
		pw.println("  LOG_DATE");
		pw.println(") values (");
		pw.println("  ?");
		pw.println(")");
		
		String sql = sw.toString();
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setTimestamp(1, log.logDate);
			pstmt.execute();
		}
		
	}
}
