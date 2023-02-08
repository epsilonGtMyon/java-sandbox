package epsilongtmyon.app.pages.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "logout", urlPatterns = { "/app/logout/*" })
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.logout();

		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		resp.sendRedirect(req.getServletContext().getContextPath() + "/app/home");
	}
}
