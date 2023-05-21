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
		
		
		final String jpql1 = """
SELECT
  e
FROM
  Emp e
WHERE
  e.createdAt >= :createdAt
ORDER BY
  e.empId
				""";
		
		TypedQuery<Emp> query1 = em.createQuery(jpql1, Emp.class);
		emf.addNamedQuery("Emp.findByAfterCreatedAt", query1);
		
		//----------------------------------
		
		final String jpql2 = """
SELECT
  e1
FROM
  Emp e1
WHERE
  e1.empId = (SELECT
                  max(e2.empId)
                FROM
                  Emp e2
                )
				""";
		
		TypedQuery<Emp> query2 = em.createQuery(jpql2, Emp.class);
		emf.addNamedQuery("Emp.findByMaxEmpId", query2);
		
	}
}
