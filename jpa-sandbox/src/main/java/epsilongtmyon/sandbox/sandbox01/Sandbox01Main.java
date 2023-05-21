package epsilongtmyon.sandbox.sandbox01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Sandbox01Main {

	private final Sandbox01ServiceA sandbox01ServiceA;
	private final Sandbox01ServiceB sandbox01ServiceB;

	public Sandbox01Main(
			Sandbox01ServiceA sandbox01ServiceA,
			Sandbox01ServiceB sandbox01ServiceB) {

		this.sandbox01ServiceA = sandbox01ServiceA;
		this.sandbox01ServiceB = sandbox01ServiceB;
	}

	public static void main(String[] args) {

		// SessionFactoryの生成は重たいのでアプリケーションで一つだけ
		try (EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpaSandbox");) {

			// こっちは短命
			try (EntityManager em = emFactory.createEntityManager()) {
				Sandbox01ServiceA sandbox01ServiceA = new Sandbox01ServiceA(em);
				Sandbox01ServiceB sandbox01ServiceB = new Sandbox01ServiceB(em);

				Sandbox01Main main = new Sandbox01Main(sandbox01ServiceA, sandbox01ServiceB);
				main.execute();

			}

		}
	}

	private void execute() {
//		sandbox01ServiceA.execute();
		sandbox01ServiceB.execute();
	}
}
