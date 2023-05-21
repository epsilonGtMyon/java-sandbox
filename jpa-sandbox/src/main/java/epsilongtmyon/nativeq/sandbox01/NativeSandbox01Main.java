package epsilongtmyon.nativeq.sandbox01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class NativeSandbox01Main {

	private final NativeSandbox01ServiceA serviceA;

	public NativeSandbox01Main(NativeSandbox01ServiceA serviceA) {
		super();
		this.serviceA = serviceA;
	}

	public static void main(String[] args) {

		try (EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpaSandbox")) {

			try (EntityManager em = emFactory.createEntityManager()) {
				NativeSandbox01ServiceA serviceA = new NativeSandbox01ServiceA(em);

				NativeSandbox01Main main = new NativeSandbox01Main(serviceA);
				main.execute();
			}
		}
	}

	private void execute() {

		serviceA.execute();
	}
}
