package epsilongtmyon.observation;

import java.util.concurrent.TimeUnit;

import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler.AllMatchingCompositeObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;

public class Sandbox02Main {


	public static void main(String[] args) throws Exception {
		SimpleMeterRegistry meterRegistry = new SimpleMeterRegistry();
		
		ObservationRegistry observationRegistry = ObservationRegistry.create();
		
		// AllMatchingCompositeObservationHandlerを使うと 複数のObservationHandlerをまとめることができる。
		AllMatchingCompositeObservationHandler handlers = new AllMatchingCompositeObservationHandler(
				new ObservationTextPublisher(),
				// DefaultMeterObservationHandlerを使うとSimpleMeterRegistryと組み合わせることができる。
				new DefaultMeterObservationHandler(meterRegistry)
				);
		observationRegistry.observationConfig().observationHandler(handlers);
		
		
		
		Sandbox02Main main = new Sandbox02Main();
		main.start1(observationRegistry);
		
		// timerが計測されている。
		System.out.println(meterRegistry.getMetersAsString());
		
		
	}
	
	private void start1(ObservationRegistry observationRegistry) throws Exception {
		
		Observation obs = Observation.createNotStarted("Sandbox03Main.start", observationRegistry);
		
		obs.observeChecked(() -> {
			TimeUnit.SECONDS.sleep(2L);
		});
		

		obs.observeChecked(() -> {
			TimeUnit.SECONDS.sleep(3L);
		});
		
	}
}
