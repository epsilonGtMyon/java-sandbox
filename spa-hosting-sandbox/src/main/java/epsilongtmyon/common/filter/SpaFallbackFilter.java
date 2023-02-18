package epsilongtmyon.common.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ページリロード時に404エラーを防ぐためのフィルタ
 */
public class SpaFallbackFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) resp;

		final String method = request.getMethod();
		if (!Objects.equals(method, "GET")) {
			// GET以外はそのまま通す
			chain.doFilter(request, response);
			return;
		}

		final String contextPath = request.getContextPath();
		final String requestUri = request.getRequestURI();

		if (isApiRequest(contextPath, requestUri)) {
			chain.doFilter(request, response);
			return;
		}

		if (isStaticResource(contextPath, requestUri)) {
			chain.doFilter(request, response);
			return;
		}

		// ここに到達した場合は フロントエンド側でページ遷移した後にF5などで再読み込みした場合とみなし
		// index.htmlをデフォルトサーブレットに委譲
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
		requestDispatcher.forward(request, response);
	}

	private boolean isApiRequest(String contextPath, String requestUri) {
		final String path = requestUri.substring(contextPath.length());
		return path.startsWith("/api/");
	}

	private boolean isStaticResource(String contextPath, String requestUri) {
		final String path = requestUri.substring(contextPath.length());

		final int lastSegmentBegin = path.lastIndexOf("/");
		final int extensionBegin = path.lastIndexOf(".");

		// bbb.css
		// aaa/bbb.css
		// のような形式
		return lastSegmentBegin < extensionBegin;
	}

}
