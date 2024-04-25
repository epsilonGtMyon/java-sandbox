package epsilongtmyon.instrumentations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import epsilongtmyon.MeterPrinter;
import epsilongtmyon.Sleeper;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class SystemMetrics01Sandbox {

	public static void main(String[] args) throws IOException {
		CompositeMeterRegistry compositeregistry = new CompositeMeterRegistry();
		compositeregistry.add(new SimpleMeterRegistry());
		
		start1(compositeregistry);
		
		
		// 定期的に出力
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleAtFixedRate(() -> {
			MeterPrinter.printMeter(compositeregistry);
		}, 0, 2, TimeUnit.SECONDS);
		
		System.in.read();
		executorService.shutdown();
		while(!executorService.isShutdown()) {
			Sleeper.sleep(TimeUnit.SECONDS, 1);
		}
	}


	private static void start1(MeterRegistry registry) {
		
		File file = Paths.get(System.getProperty("user.home")).toFile();
		DiskSpaceMetrics dsMetrics = new DiskSpaceMetrics(file);
		dsMetrics.bindTo(registry);
		
		ProcessorMetrics psMetrics = new ProcessorMetrics();
		psMetrics.bindTo(registry);
		
		UptimeMetrics utMetrics = new UptimeMetrics();
		utMetrics.bindTo(registry);
	}
}
