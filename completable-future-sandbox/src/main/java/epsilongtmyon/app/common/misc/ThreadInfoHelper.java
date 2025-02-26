package epsilongtmyon.app.common.misc;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadInfoHelper {

	private static Logger logger = Logger.getLogger(ThreadInfoHelper.class.getName());

	public static void withThreadName(String message) {

		logger.log(Level.INFO, "ThreadName={0}:" + message, new Object[] { Thread.currentThread().getName() });
	}

}
