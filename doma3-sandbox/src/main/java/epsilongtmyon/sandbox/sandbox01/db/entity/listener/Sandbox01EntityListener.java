package epsilongtmyon.sandbox.sandbox01.db.entity.listener;

import java.time.LocalDateTime;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

import epsilongtmyon.sandbox.sandbox01.db.entity.Sandbox01Entity;

public class Sandbox01EntityListener implements EntityListener<Sandbox01Entity> {

	@Override
	public void preInsert(Sandbox01Entity entity, PreInsertContext<Sandbox01Entity> context) {
		LocalDateTime now = LocalDateTime.now();
		entity.setCreatedAt(now);
		entity.setUpdatedAt(now);
	}

	@Override
	public void preUpdate(Sandbox01Entity entity, PreUpdateContext<Sandbox01Entity> context) {
		LocalDateTime now = LocalDateTime.now();
		entity.setUpdatedAt(now);
	}

	
	
}
