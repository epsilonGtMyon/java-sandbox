package epsilongtmyon.app.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	private static Logger logger = Logger.getLogger(EncodingFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("EncodingFilter:begin");
		
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}

}