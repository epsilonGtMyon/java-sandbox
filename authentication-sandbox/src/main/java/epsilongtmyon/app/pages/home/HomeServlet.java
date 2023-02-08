package epsilongtmyon.app.pages.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsilongtmyon.shared.dispatch.JspDispatcher;

@WebServlet(name = "home", urlPatterns = { "/app/home/*" })
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new JspDispatcher().forward(req, resp, "home/index.jsp");
	}
}
