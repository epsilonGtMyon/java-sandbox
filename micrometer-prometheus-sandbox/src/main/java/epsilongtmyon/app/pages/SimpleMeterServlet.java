package epsilongtmyon.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.logging.Logger;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "simpleMeter", urlPatterns = { "/app/simpleMeter" })
public class SimpleMeterServlet extends HttpServlet {
	
	private Logger logger = Logger.getLogger(SimpleMeterServlet.class.getName());


	private SimpleMeterRegistry simpleMeterRegistry; 

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		MeterRegistry meterRegistry = (MeterRegistry)config.getServletContext().getAttribute("meterRegistry");
		this.simpleMeterRegistry = extractSimpleMeterRegistry(meterRegistry);
	}
	
	private SimpleMeterRegistry extractSimpleMeterRegistry(MeterRegistry meterRegistry) {
		if(meterRegistry instanceof SimpleMeterRegistry smr) {
			return smr;
		}
		if (meterRegistry instanceof CompositeMeterRegistry cmr) {
			return cmr.getRegistries()
					.stream()
					.map(m -> extractSimpleMeterRegistry(m))
					.filter(Objects::nonNull)
					.findFirst()
					.orElse(null);
		}
		
		return null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String str = simpleMeterRegistry == null ? "" : simpleMeterRegistry.getMetersAsString();
		logger.info(str);
		PrintWriter pw = resp.getWriter();
		pw.println(str);
	}
}
