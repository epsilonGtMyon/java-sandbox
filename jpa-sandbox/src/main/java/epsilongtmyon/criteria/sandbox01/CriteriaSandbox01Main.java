package epsilongtmyon.criteria.sandbox01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CriteriaSandbox01Main {

	private final CriteriaSandbox01ServiceA serviceA;

	public CriteriaSandbox01Main(CriteriaSandbox01ServiceA serviceA) {
		super();
		this.serviceA = serviceA;
	}

	public static void main(String[] args) {

		try (EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpaSandbox")) {

			try (EntityManager em = emFactory.createEntityManager()) {
				CriteriaSandbox01ServiceA serviceA = new CriteriaSandbox01ServiceA(em);

				CriteriaSandbox01Main main = new CriteriaSandbox01Main(serviceA);
				main.execute();
			}
		}
	}

	private void execute() {

		serviceA.execute();
	}
}
