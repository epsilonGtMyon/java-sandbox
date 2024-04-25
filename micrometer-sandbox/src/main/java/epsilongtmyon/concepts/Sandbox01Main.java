package epsilongtmyon.concepts;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class Sandbox01Main {

	public static void main(String[] args) {
		SimpleMeterRegistry registry = new SimpleMeterRegistry();
		registry.config().commonTags("env", "dev");
		start3(registry);
		System.out.println(registry.getMetersAsString());
	}

	private static void start1(MeterRegistry registry) {

		Counter counter1 = registry.counter("counter1", "tag1", "value1");
		counter1.increment();
		counter1.increment();

		Counter counter2 = registry.counter("counter2", "tag2", "value1");
		counter2.increment();

	}

	private static void start2(MeterRegistry registry) {
		Counter counter = Counter
				.builder("counter")
				.baseUnit("beans") // optional
				.description("a description of what this counter does") // optional
				.tags("region", "test") // optional
				.register(registry);

		counter.increment();
		counter.increment();
		counter.increment();
		counter.increment();
	}

	private static void start3(MeterRegistry registry) {
		Hoge hoge = new Hoge();
		FunctionCounter fCounter = FunctionCounter
				.builder("counter", hoge, h -> h.up())
				.register(registry);
		
		fCounter.count();
		fCounter.count();
	}

	static class Hoge {
		private double current;;
		public double up() {
			System.out.println("up");
			current += 2;
			return current;
		}
	}
}
