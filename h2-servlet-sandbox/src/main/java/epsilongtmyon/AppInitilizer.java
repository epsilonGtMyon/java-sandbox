package epsilongtmyon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.h2.Driver;
import org.h2.server.web.WebServlet;

import epsilongtmyon.app.common.db.ConnectionProvider;
import epsilongtmyon.app.common.db.dao.InitilizeDao;
import epsilongtmyon.app.log.LogServlet;

public class AppInitilizer implements ServletContainerInitializer {
	
	private final ConnectionProvider provider = new ConnectionProvider();

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		System.out.println("AppInitilizer");

		//
		LogServlet logServlet = ctx.createServlet(LogServlet.class);
		Dynamic logServletDynamic = ctx.addServlet("log", logServlet);
		logServletDynamic.addMapping("/log");

		//h2
		WebServlet webServlet = ctx.createServlet(WebServlet.class);
		Dynamic webServletDynamic = ctx.addServlet("web", webServlet);
		webServletDynamic.addMapping("/h2-console/*");

		prepareDatabase();
	}

	private void prepareDatabase() {
		Driver.load();

		InitilizeDao dao = new InitilizeDao();

		try (Connection con = provider.getConnection()) {
			dao.initilize(con);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
