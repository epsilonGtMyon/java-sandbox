package epsilongtmyon.api.sandbox01;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsilongtmyon.api.sandbox01.spec.Sandbox01Request;
import epsilongtmyon.api.sandbox01.spec.Sandox01Response;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

@WebServlet(name = "sandbox01", urlPatterns = "/api/sandbox01/*")
public class Sandbox01Servlet extends HttpServlet {

	// thread safe??
	private final Jsonb jsonb = JsonbBuilder.create();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req.getParameter("value01"), resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Sandbox01Request request = jsonb.fromJson(req.getInputStream(), Sandbox01Request.class);
		doService(request.getValue01(), resp);
	}

	private void doService(String value01, HttpServletResponse resp) throws ServletException, IOException {

		try {
			final Sandox01Response response = new Sandox01Response();
			response.setValue01("hello " + value01);
			response.setValue02(LocalDateTime.now().toString());

			resp.setContentType("application/json");
			resp.setCharacterEncoding("utf-8");
			PrintWriter writer = resp.getWriter();
			jsonb.toJson(response, writer);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


}
