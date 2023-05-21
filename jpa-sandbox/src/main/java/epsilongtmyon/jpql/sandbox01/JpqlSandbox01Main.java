package epsilongtmyon.jpql.sandbox01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpqlSandbox01Main {

	private final JpqlSandbox01NamedQueryPrepareService jpqlSandbox01NamedQueryPrepareService;
	private final JpqlSandbox01ServiceA jpqlSandbox01ServiceA;
	private final JpqlSandbox01ServiceB jpqlSandbox01ServiceB;
	private final JpqlSandbox01ServiceC jpqlSandbox01ServiceC;

	public JpqlSandbox01Main(
			JpqlSandbox01NamedQueryPrepareService jpqlSandbox01NamedQueryPrepareService,
			JpqlSandbox01ServiceA jpqlSandbox01ServiceA,
			JpqlSandbox01ServiceB jpqlSandbox01ServiceB,
			JpqlSandbox01ServiceC jpqlSandbox01ServiceC) {
		this.jpqlSandbox01NamedQueryPrepareService=jpqlSandbox01NamedQueryPrepareService;
		this.jpqlSandbox01ServiceA = jpqlSandbox01ServiceA;
		this.jpqlSandbox01ServiceB = jpqlSandbox01ServiceB;
		this.jpqlSandbox01ServiceC = jpqlSandbox01ServiceC;
	}

	public static void main(String[] args) {

		try (EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpaSandbox")) {

			try (EntityManager em = emFactory.createEntityManager()) {

				JpqlSandbox01NamedQueryPrepareService namedQueryPrepareService = new JpqlSandbox01NamedQueryPrepareService(emFactory, em);
				JpqlSandbox01ServiceA serviceA = new JpqlSandbox01ServiceA(em);
				JpqlSandbox01ServiceB serviceB = new JpqlSandbox01ServiceB(em);
				JpqlSandbox01ServiceC serviceC = new JpqlSandbox01ServiceC(em);

				JpqlSandbox01Main main = new JpqlSandbox01Main(namedQueryPrepareService, serviceA, serviceB, serviceC);
				main.execute();

			}

		}
	}

	public void execute() {
		jpqlSandbox01NamedQueryPrepareService.preateNamedQuery();
		
		
		//		jpqlSandbox01ServiceA.execute();
		//		jpqlSandbox01ServiceB.execute();
		jpqlSandbox01ServiceC.execute();
	}
}
