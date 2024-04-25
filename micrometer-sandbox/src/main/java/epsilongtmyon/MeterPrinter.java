package epsilongtmyon;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class MeterPrinter {

	public static void printMeter(MeterRegistry registry) {

		if (registry instanceof CompositeMeterRegistry cmr) {
			cmr.getRegistries().forEach(MeterPrinter::printMeter);
		} else if (registry instanceof SimpleMeterRegistry smr) {
			System.out.println("====");
			System.out.println(smr.getMetersAsString());
		}
	}
}
