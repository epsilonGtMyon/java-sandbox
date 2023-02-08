package epsilongtmyon.shared.dispatch;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JspDispatcher {

	private final String prefix;

	public JspDispatcher() {
		prefix = "/WEB-INF/pages/";
	}

	public void forward(ServletRequest request, ServletResponse response, String jsp)
			throws ServletException, IOException {
		String jspPath = prefix + jsp;

		RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
		dispatcher.forward(request, response);
	}
}
