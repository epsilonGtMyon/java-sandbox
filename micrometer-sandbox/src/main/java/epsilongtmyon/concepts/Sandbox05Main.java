package epsilongtmyon.concepts;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import epsilongtmyon.MeterPrinter;
import epsilongtmyon.Sleeper;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class Sandbox05Main {


	public static void main(String[] args) throws InterruptedException {
		CompositeMeterRegistry compositeregistry = new CompositeMeterRegistry();
		compositeregistry.add(new SimpleMeterRegistry());
		start1(compositeregistry);

		MeterPrinter.printMeter(compositeregistry);
		
	}
	
	private static void start1(MeterRegistry registry) throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(3);
		
		
		final LongTaskTimer tasks = registry.more().longTaskTimer("myTasks");
		
		// -------------------------------
		new Thread(() -> {
			tasks.record(() -> {
				Sleeper.sleep(TimeUnit.SECONDS, 5L);
				latch.countDown();
			});
		}).start();
		// -------------------------------
		new Thread(() -> {
			tasks.record(() -> {
				Sleeper.sleep(TimeUnit.SECONDS, 6L);
				latch.countDown();
			});
		}).start();
		// -------------------------------
		new Thread(() -> {
			tasks.record(() -> {
				Sleeper.sleep(TimeUnit.SECONDS, 7L);
				latch.countDown();
			});
		}).start();

		// =====================================
		
		while(latch.getCount() > 0) {
			MeterPrinter.printMeter(registry);
			Sleeper.sleep(TimeUnit.SECONDS, 1L);
		}
		
	}
}
