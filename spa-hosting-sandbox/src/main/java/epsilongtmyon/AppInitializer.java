package epsilongtmyon;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

import epsilongtmyon.common.filter.EncodingFilter;
import epsilongtmyon.common.filter.SpaFallbackFilter;

@WebListener
public class AppInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		final ServletContext servletContext = sce.getServletContext();

		try {
			EncodingFilter encodingFilter = servletContext.createFilter(EncodingFilter.class);
			Dynamic encodingFilterDynamic = servletContext.addFilter("encoding", encodingFilter);
			encodingFilterDynamic.addMappingForUrlPatterns(null, false, "/*");

			SpaFallbackFilter spaFallbackFilter = servletContext.createFilter(SpaFallbackFilter.class);
			Dynamic spaFallbackFilterDynamic = servletContext.addFilter("spaFallback", spaFallbackFilter);
			spaFallbackFilterDynamic.addMappingForUrlPatterns(null, false, "/*");

		} catch (ServletException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
