package epsilongtmyon.converter.sandbox01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConverterSandbox01Main {

	private final ConverterSandbox01ServiceA serviceA;
	private final ConverterSandbox01ServiceB serviceB;

	public ConverterSandbox01Main(
			ConverterSandbox01ServiceA serviceA,
			ConverterSandbox01ServiceB serviceB) {
		super();
		this.serviceA = serviceA;
		this.serviceB = serviceB;
	}

	public static void main(String[] args) {

		try (EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpaSandbox")) {

			try (EntityManager em = emFactory.createEntityManager()) {
				ConverterSandbox01ServiceA serviceA = new ConverterSandbox01ServiceA(em);
				ConverterSandbox01ServiceB serviceB = new ConverterSandbox01ServiceB(em);

				ConverterSandbox01Main main = new ConverterSandbox01Main(serviceA, serviceB);
				main.execute();
			}
		}
	}

	private void execute() {

		//serviceA.execute();
		serviceB.execute();
	}
}
