package epsilongtmyon.app.pages.sandbox01;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sandbox01Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Principal principal = req.getUserPrincipal();
		System.out.println(principal == null ? null : principal.getName());

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/sandbox01/index.jsp");
		dispatcher.forward(req, resp);

	}

}
