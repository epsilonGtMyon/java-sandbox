package epsilongtmyon.app.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {

	private static Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());

	private static Set<String> permittedPaths = Set.of("/app/login", "/app/logout");

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		logger.info("AuthenticationFilter:begin");

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) resp;

		if (isAuthenticated(request)) {
			// 認証済
			chain.doFilter(request, response);
			return;
		}

		if (isPermittedPath(request)) {
			// 許可してるパス
			chain.doFilter(request, response);
			return;
		}

		// 上記以外はログインページに
		response.sendRedirect(req.getServletContext().getContextPath() + "/app/login");
	}

	private boolean isAuthenticated(HttpServletRequest request) {
		final Principal principal = request.getUserPrincipal();
		return principal != null;
	}

	private boolean isPermittedPath(HttpServletRequest request) {
		final String contextPath = request.getContextPath();
		final String requestURI = request.getRequestURI();

		final String path = Objects.equals(contextPath, "/")
				? requestURI
				: request.getRequestURI().substring(contextPath.length());

		return permittedPaths.contains(path);
	}

}
