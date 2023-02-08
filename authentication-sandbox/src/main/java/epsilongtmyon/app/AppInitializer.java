package epsilongtmyon.app;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebListener;

import epsilongtmyon.app.filter.AuthenticationFilter;
import epsilongtmyon.app.filter.EncodingFilter;

@WebListener
public class AppInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		final ServletContext servletContext = sce.getServletContext();

		try {
			EncodingFilter encodingFilter = servletContext.createFilter(EncodingFilter.class);
			Dynamic encodingFilterDynamic = servletContext.addFilter("encoding", encodingFilter);
			encodingFilterDynamic.addMappingForUrlPatterns(null, false, "/app/*");

			AuthenticationFilter authenticationFilter = servletContext.createFilter(AuthenticationFilter.class);
			Dynamic authenticationFilterDynamic = servletContext.addFilter("authentication", authenticationFilter);
			authenticationFilterDynamic.addMappingForUrlPatterns(null, false, "/app/*");

		} catch (ServletException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		final SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
		// cookie名でjavaだというのがバレるのが嫌な場合などは名前を変える
		sessionCookieConfig.setName("my-cookie");
	}

}
