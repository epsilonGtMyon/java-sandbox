package epsilongtmyon.concepts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import epsilongtmyon.MeterPrinter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class Sandbox02Main {

	public static void main(String[] args) {
		CompositeMeterRegistry compositeregistry = new CompositeMeterRegistry();
		compositeregistry.add(new SimpleMeterRegistry());
		start1(compositeregistry);

		MeterPrinter.printMeter(compositeregistry);
	}

	private static void start1(MeterRegistry registry) {
		registry.gauge("hoge", 8);//プリミティブやそのラッパーの場合はこちらが有効
		registry.gauge("hoge", 10);

		registry.gauge("foo", Tags.of("a", "b1"), 12);
	}

	private static void start2(MeterRegistry registry) {
		List<Object> list = new ArrayList<>();
		registry.gaugeCollectionSize("ref", Collections.emptyList(), list);

		MeterPrinter.printMeter(registry);

		list.add("");
	}

	private static void start3(MeterRegistry registry) {
		AtomicInteger i1 = registry.gauge("ref", new AtomicInteger(1));

		MeterPrinter.printMeter(registry);

		i1.set(5);
	}
}
