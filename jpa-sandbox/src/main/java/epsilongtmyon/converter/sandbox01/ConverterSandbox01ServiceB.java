package epsilongtmyon.converter.sandbox01;

import java.util.List;

import epsilongtmyon.db.entity.ConverterSandbox;
import epsilongtmyon.db.vo.HogeKbn;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ConverterSandbox01ServiceB {

	private final EntityManager em;

	public ConverterSandbox01ServiceB(EntityManager em) {
		super();
		this.em = em;
	}

	public void execute() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			executeInternal02();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	private void executeInternal01() {
		
		final String jpql = "DELETE FROM ConverterSandbox c";
		em.createQuery(jpql).executeUpdate();
		
		ConverterSandbox c01 = new ConverterSandbox();
		c01.setTextEnumValue01(HogeKbn.A);
		em.persist(c01);

		ConverterSandbox c02 = new ConverterSandbox();
		c02.setTextEnumValue01(HogeKbn.B);
		em.persist(c02);

		ConverterSandbox c03 = new ConverterSandbox();
		em.persist(c03);
	}


	private void executeInternal02() {
		final String jpql = "SELECT c FROM ConverterSandbox c ORDER BY c.seq"; 
		List<ConverterSandbox> results = em.createQuery(jpql, ConverterSandbox.class).getResultList();
		
		results.forEach(x -> {
			System.out.println(x);
		});
	}

}
