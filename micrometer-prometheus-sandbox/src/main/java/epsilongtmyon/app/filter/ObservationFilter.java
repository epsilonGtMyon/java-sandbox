package epsilongtmyon.app.filter;

import java.io.IOException;

import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Scope;
import io.micrometer.observation.ObservationRegistry;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ObservationFilter extends HttpFilter {

	private ObservationRegistry observationRegistry;

	@Override
	public void init() throws ServletException {
		super.init();

		final ServletContext servletContext = getServletContext();
		this.observationRegistry = (ObservationRegistry) servletContext.getAttribute("observationRegistry");
	}

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		String url = req.getRequestURI();
		String userAgent = req.getHeader("user-agent");
		String remoteAddr = req.getRemoteAddr();

		Observation observation = Observation.createNotStarted(ObservationFilter.class.getName(), observationRegistry);
		observation.lowCardinalityKeyValue("url", url);
		observation.highCardinalityKeyValue("userAgent", userAgent);
		observation.highCardinalityKeyValue("remoteAddr", remoteAddr);
		observation.start();
		try (Scope scope = observation.openScope()) {
			chain.doFilter(req, res);
		} catch (Throwable t) {
			observation.error(t);
			throw t;
		} finally {
			observation.stop();
		}

	}

}
