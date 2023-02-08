package epsilongtmyon.app.pages.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsilongtmyon.app.pages.login.spec.LoginRequest;
import epsilongtmyon.app.pages.login.spec.LoginView;
import epsilongtmyon.shared.dispatch.JspDispatcher;

@WebServlet(name = "login", urlPatterns = { "/app/login/*" })
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new JspDispatcher().forward(req, resp, "login/index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		final LoginRequest loginRequest = LoginRequest.fromServletRequest(req);
		final LoginView view = new LoginView();
		
		req.setAttribute("loginRequest", loginRequest);
		req.setAttribute("view", view);

		if (login(req, loginRequest)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/app/home");
		} else {
			view.setMessage("ログインに失敗しました。");
			new JspDispatcher().forward(req, resp, "login/index.jsp");
		}

	}

	private boolean login(HttpServletRequest req, LoginRequest loginRequest) {
		try {
			req.login(loginRequest.getUserId(), loginRequest.getPassword());
			return true;
		} catch (ServletException e) {
			e.printStackTrace();
			return false;
		}
	}

}
