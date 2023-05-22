package epsilongtmyon.db.entity.common;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

// AbstractのEntityListenerではなく
// AbstractEntityのListener
public class AbstractEntityListener {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PrePersist
	public void prePersist(Object entity) {
		logger.info("pre persist {}", entity);

		if (entity instanceof AbstractEntity ae) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			ae.setCreatedAt(now);
			ae.setUpdatedAt(now);
		}

	}

	@PreUpdate
	public void preUpdate(Object entity) {
		logger.info("pre update {}", entity);

		if (entity instanceof AbstractEntity ae) {
			Timestamp now = new Timestamp(System.currentTimeMillis());

			ae.setUpdatedAt(now);
		}
	}
}
