package epsilongtmyon.concepts;

import epsilongtmyon.MeterPrinter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class Sandbox04Main {


	public static void main(String[] args) {
		CompositeMeterRegistry compositeregistry = new CompositeMeterRegistry();
		compositeregistry.add(new SimpleMeterRegistry());
		start1(compositeregistry);

		MeterPrinter.printMeter(compositeregistry);
		
	}

	private static void start1(MeterRegistry registry) {
		
		DistributionSummary summary = registry.summary("response.size");
		
		summary.record(50);
		summary.record(4);
		summary.record(20);
		
		DistributionSummary summary2 = DistributionSummary
			    .builder("response.size")
			    .description("a description of what this summary does") // optional
			    .baseUnit("bytes") // optional 
			    .tags("region", "test") // optional
			    .scale(100) // optional 
			    .register(registry);

		summary2.record(50);
		summary2.record(4);
		summary2.record(20);
		
	}
	
}
