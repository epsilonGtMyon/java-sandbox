package epsilongtmyon;

import java.util.concurrent.TimeUnit;

public class Sleeper {

	public static void sleep(TimeUnit unit, long amount) {

		try {
			unit.sleep(amount);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
