package epsilongtmyon.app.common.misc;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sleeper {

	private static final Logger logger = Logger.getLogger(Sleeper.class.getName());

	public static void sleepMills(long mills) {

		try {
			TimeUnit.MILLISECONDS.sleep(mills);
		} catch (InterruptedException ignore) {
			logger.log(Level.WARNING, "interrupted");
		}
	}
}
