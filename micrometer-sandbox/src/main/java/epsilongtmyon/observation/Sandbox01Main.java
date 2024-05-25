package epsilongtmyon.observation;

import io.micrometer.common.KeyValues;
import io.micrometer.observation.GlobalObservationConvention;
import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.Observation.Event;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;

public class Sandbox01Main {

	public static void main(String[] args) throws Exception {
		ObservationRegistry r = ObservationRegistry.create();
		// イベントをハンドリング(ここではログ出力)
		r.observationConfig().observationHandler(new ObservationTextPublisher());
		// Contextを加工するもの
		r.observationConfig().observationConvention(new GlobalObservationConvention() {

			@Override
			public boolean supportsContext(Context context) {
				return true;
			}

			@Override
			public KeyValues getLowCardinalityKeyValues(Context context) {
				return KeyValues.of("ABC", "DEF");
			}

		});

		Sandbox01Main main = new Sandbox01Main();
		main.start01(r);

	}

	private void start01(ObservationRegistry r) throws Exception {
		Observation ob1 = Observation.createNotStarted("aaa", r)
				.highCardinalityKeyValue("aaaaaa", "bbbb")
				.lowCardinalityKeyValue("abc1", "111");
		ob1.observe(() -> {
			System.out.println("hello");

			// 任意のイベント発行とかもできる。
			ob1.event(Event.of("Sandbox01Main:Event", "event1"));
		});

		ob1.observe(() -> {
			throw new RuntimeException("ee");
		});

	}
}
