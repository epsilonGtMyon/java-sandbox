package epsilongtmyon.app.sandbox01;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import epsilongtmyon.app.common.misc.Sleeper;

/**
 * @author epsil
 *
 */
public class Sandbox01Task {

	private static final Logger logger = Logger.getLogger(Sandbox01Task.class.getName());

	private String id;

	private LocalDateTime beginDateTime;

	private LocalDateTime endDateTime;

	private long sleepMills;

	public Sandbox01Task(String id, LocalDateTime beginDateTime, LocalDateTime endDateTime, long sleepMills) {
		super();
		this.id = id;
		this.beginDateTime = beginDateTime;
		this.endDateTime = endDateTime;
		this.sleepMills = sleepMills;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getBeginDateTime() {
		return beginDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public long getSleepMills() {
		return sleepMills;
	}

	@Override
	public String toString() {
		return "Sandbox01Task [id=" + id + ", beginDateTime=" + beginDateTime + ", endDateTime=" + endDateTime
				+ ", sleepMills=" + sleepMills + "]";
	}

	public static Sandbox01Task doTask(String id, long sleepMills) {
		LocalDateTime beginDateTime = LocalDateTime.now();

		logger.log(Level.INFO, "begin Task ThreadName={0} id={1} sleepMills={2}",
				new Object[] { Thread.currentThread().getName(), id, sleepMills });
		Sleeper.sleepMills(sleepMills);

		LocalDateTime endDateTime = LocalDateTime.now();
		logger.log(Level.INFO, "end Task ThreadName={0} id={1}", new Object[] { Thread.currentThread().getName(), id });

		return new Sandbox01Task(id, beginDateTime, endDateTime, sleepMills);
	}

	public String format() {
		logger.log(Level.INFO, "format Task ThreadName={0} id={1}",
				new Object[] { Thread.currentThread().getName(), id });

		return id + ":" + beginDateTime + "-" + endDateTime;
	}

}
