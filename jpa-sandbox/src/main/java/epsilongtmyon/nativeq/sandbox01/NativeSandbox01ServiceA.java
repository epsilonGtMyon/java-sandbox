package epsilongtmyon.nativeq.sandbox01;

import java.util.Arrays;
import java.util.List;

import epsilongtmyon.db.entity.Emp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class NativeSandbox01ServiceA {

	private final EntityManager em;

	public NativeSandbox01ServiceA(EntityManager em) {
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
		// 
		final String sql = """
SELECT
  *
FROM
  EMP
WHERE
  BLOOD_TYPE = ?1
				""";
		
		Query query = em.createNativeQuery(sql, Emp.class)
			.setParameter(1, "AB");
		
		
		List<Emp> emps = query.getResultList();
		
		if(! emps.isEmpty()) {
			Emp emp = emps.get(0);
			
			emp.setNote("aaaaaa");
			
			//管理状態になってるので実行できる。
			em.merge(emp);
			//em.remove(emp);
		}
	}
	


	private void executeInternal02() {
		// 
		final String sql = """
SELECT
  E.EMP_ID
 ,E.BLOOD_TYPE
FROM
  EMP E
WHERE
  E.BLOOD_TYPE = ?1
				""";
		
		Query query = em.createNativeQuery(sql, Object[].class)
			.setParameter(1, "A");
		
		
		List<Object[]> emps = query.getResultList();
		
		//型でマッピングできないのか?
		emps.forEach(e -> {
			System.out.println(Arrays.toString(e));
		});
	}

}
