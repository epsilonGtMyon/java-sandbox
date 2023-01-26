package epsilongtmyon.app.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsilongtmyon.app.common.db.ConnectionProvider;
import epsilongtmyon.app.common.db.dao.LogDao;
import epsilongtmyon.app.common.db.entity.Log;

public class LogServlet extends HttpServlet {

	private final ConnectionProvider provider = new ConnectionProvider();

	private final LogDao logDao = new LogDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ログテーブルに残す
		try (Connection con = provider.getConnection()) {
			Log log = new Log();
			log.logDate = new Timestamp(System.currentTimeMillis());
			logDao.insert(con, log);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("  <head>");
		pw.println("  </head>");
		pw.println("<body>");
		pw.println("   logServlet");
		pw.println("   <div>");
		pw.println("     <a href=\"./h2-console\" target=\"_blank\">h2console</a>");
		pw.println("   </div>");
		pw.println("</body>");
		pw.println("</html>");
	}

}
