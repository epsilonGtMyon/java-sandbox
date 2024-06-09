package epsilongtmyon.app;

import java.io.File;
import java.nio.file.Paths;

import epsilongtmyon.app.filter.EncodingFilter;
import epsilongtmyon.app.filter.ObservationFilter;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.observation.ObservationHandler.AllMatchingCompositeObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;
import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import io.prometheus.metrics.exporter.servlet.jakarta.PrometheusMetricsServlet;
import jakarta.servlet.FilterRegistration.Dynamic;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServlet;

@WebListener
public class AppInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		final ServletContext servletContext = sce.getServletContext();

		try {
			EncodingFilter encodingFilter = servletContext.createFilter(EncodingFilter.class);
			Dynamic encodingFilterDynamic = servletContext.addFilter("encoding", encodingFilter);
			encodingFilterDynamic.addMappingForUrlPatterns(null, false, "/app/*");

			ObservationFilter observationFilter = servletContext.createFilter(ObservationFilter.class);
			Dynamic observationFilterDynamic = servletContext.addFilter("observation", observationFilter);
			observationFilterDynamic.addMappingForUrlPatterns(null, false, "/app/*");

		} catch (ServletException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		final SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
		sessionCookieConfig.setName("my-cookie");
		
		// =================================
		// micromter の設定
		CompositeMeterRegistry registry = new CompositeMeterRegistry();
		servletContext.setAttribute("meterRegistry", registry);
		
		SimpleMeterRegistry simpleMeterRegistry = new SimpleMeterRegistry();
		registry.add(simpleMeterRegistry);
		
		File file = Paths.get(System.getProperty("user.home")).toFile();
		DiskSpaceMetrics dsMetrics = new DiskSpaceMetrics(file);
		dsMetrics.bindTo(registry);
		
		ProcessorMetrics psMetrics = new ProcessorMetrics();
		psMetrics.bindTo(registry);
		
		UptimeMetrics utMetrics = new UptimeMetrics();
		utMetrics.bindTo(registry);
		
		
		// -------------
		// micromter-observation
		ObservationRegistry observationRegistry = ObservationRegistry.create();
		servletContext.setAttribute("observationRegistry", observationRegistry);

		AllMatchingCompositeObservationHandler handlers = new AllMatchingCompositeObservationHandler(
				new ObservationTextPublisher(),
				new DefaultMeterObservationHandler(registry)
				);
		observationRegistry.observationConfig().observationHandler(handlers);
		

		// -------------
		// micromter-prometheus の設定
		PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
		registry.add(prometheusRegistry);
		
		// prometheusが収集するためのエンドポイントを追加
		HttpServlet prometheusMetricsServlet = new PrometheusMetricsServlet(prometheusRegistry.getPrometheusRegistry());
		jakarta.servlet.ServletRegistration.Dynamic prometheusMetricsServletDynamic = servletContext.addServlet("prometheusMetricsServlet", prometheusMetricsServlet);
		prometheusMetricsServletDynamic.addMapping("/prometheus");
		
	}

}
