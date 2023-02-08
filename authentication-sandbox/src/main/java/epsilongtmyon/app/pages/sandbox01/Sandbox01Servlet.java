package epsilongtmyon.app.pages.sandbox01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsilongtmyon.shared.dispatch.JspDispatcher;

@WebServlet(name = "sandbox01", urlPatterns = { "/app/sandbox01/*" })
public class Sandbox01Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new JspDispatcher().forward(req, resp, "sandbox01/index.jsp");
	}
}
