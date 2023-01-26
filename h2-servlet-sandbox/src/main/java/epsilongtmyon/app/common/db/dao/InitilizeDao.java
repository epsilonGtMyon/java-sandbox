package epsilongtmyon.app.common.db.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitilizeDao {

	public void initilize(Connection con) throws SQLException {
		try (Statement stmt = con.createStatement()) {

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println("create table if not exists LOG(");
			pw.println("  ID       int        default 0 not null auto_increment ");
			pw.println("  ,LOG_DATE timestamp ");
			pw.println("  ,constraint PK_LOG primary key (ID)");
			pw.println(")");

			stmt.execute(sw.toString());
		}
	}
}
