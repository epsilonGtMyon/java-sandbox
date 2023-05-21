package epsilongtmyon.jpql.sandbox01;

import epsilongtmyon.db.entity.Emp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class JpqlSandbox01NamedQueryPrepareService {

	private final EntityManagerFactory emf;
	private final EntityManager em;

	public JpqlSandbox01NamedQueryPrepareService(
			EntityManagerFactory emf,
			EntityManager em) {
		this.emf = emf;
		this.em = em;
	}

	public void preateNamedQuery() {
		
		
		final String jpql = """
SELECT
  e
FROM
  Emp e
WHERE
  e.createdAt >= :createdAt
ORDER BY
  e.empId
				""";
		
		TypedQuery<Emp> query = em.createQuery(jpql, Emp.class);
		emf.addNamedQuery("Emp.findByAfterCreatedAt", query);
		
	}
}
