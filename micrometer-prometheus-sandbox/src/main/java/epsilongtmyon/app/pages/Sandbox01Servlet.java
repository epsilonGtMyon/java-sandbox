package epsilongtmyon.app.pages;

import java.io.IOException;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "sandbox01", urlPatterns = { "/app/sandbox01/*" })
public class Sandbox01Servlet extends HttpServlet {
	

	private Counter counter;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		MeterRegistry meterRegistry = (MeterRegistry)config.getServletContext().getAttribute("meterRegistry");
		counter = meterRegistry.counter("counter1", "servlet", "sandbox01");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		counter.increment();
		req.getRequestDispatcher("/WEB-INF/pages/sandbox01/index.jsp").forward(req, resp);
	}

}
