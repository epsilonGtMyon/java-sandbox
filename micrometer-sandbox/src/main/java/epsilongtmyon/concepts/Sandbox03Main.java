package epsilongtmyon.concepts;

import java.util.concurrent.TimeUnit;

import epsilongtmyon.MeterPrinter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Timer.Sample;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class Sandbox03Main {

	public static void main(String[] args) {
		CompositeMeterRegistry compositeregistry = new CompositeMeterRegistry();
		compositeregistry.add(new SimpleMeterRegistry());
		start3(compositeregistry);

		MeterPrinter.printMeter(compositeregistry);

	}

	private static void start1(MeterRegistry registry) {

		Timer timer = registry.timer("time1", Tags.of("a", "b"));
		timer.record(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		timer.record(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(timer.totalTime(TimeUnit.MILLISECONDS));
	}

	private static void start2(MeterRegistry registry) {

		Timer timer = registry.timer("time1", Tags.of("a", "b"));
		Runnable run = timer.wrap(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		run.run();
		System.out.println(timer.totalTime(TimeUnit.MILLISECONDS));
		run.run();
		System.out.println(timer.totalTime(TimeUnit.MILLISECONDS));
	}

	private static void start3(MeterRegistry registry) {

		Sample sample = Timer.start();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// タイマーを止めるときにタグを作ることができる。
		Timer timer = registry.timer("time1", Tags.of("a", "b"));
		sample.stop(timer);
	}

}
