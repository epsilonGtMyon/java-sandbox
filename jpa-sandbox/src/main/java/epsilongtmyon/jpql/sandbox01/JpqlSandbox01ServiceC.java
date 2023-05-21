package epsilongtmyon.jpql.sandbox01;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import epsilongtmyon.db.entity.Emp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class JpqlSandbox01ServiceC {

	private final EntityManager em;

	public JpqlSandbox01ServiceC(EntityManager em) {
		super();
		this.em = em;
	}

	public void execute() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			executeInternal03();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	private void executeInternal01() {
		Emp emp = em.find(Emp.class, "E0002");
		System.out.println(emp);

		//----------------------------

		final String jpql1 = """
				UPDATE Emp e SET
				  e.note = :note
				WHERE
				  e.empId = :empId
								""";

		final String paramNote = "note:" + UUID.randomUUID().toString();
		final String paramEmpId = "E0002";

		int updated = em.createQuery(jpql1)
				.setParameter("note", paramNote)
				.setParameter("empId", paramEmpId)
				.executeUpdate();

		System.out.println("updated = %d".formatted(updated));

		//----------------------------

		// この時はemp.noteには反映されていない
		System.out.println(emp);

		// 取り直すといける
		em.refresh(emp);
		System.out.println(emp);
	}

	private void executeInternal02() {
		final String jpql1 = """
				DELETE FROM Emp e1
				WHERE
				  e1.empId = (
				          SELECT
				            max(e2.empId)
				          FROM
				            Emp e2
				  )
								""";

		int deleted = em.createQuery(jpql1)
				.executeUpdate();

		System.out.println("deleted = %d".formatted(deleted));
	}

	// NamedQuery
	private void executeInternal03() {

		// Entityに@NamedQueryで記述したもの
		List<Emp> emps1 = em.createNamedQuery("Emp.findByBloodType", Emp.class)
				.setParameter("bloodType", "B")
				.getResultList();

		emps1.forEach(e -> {
			System.out.println(e);
		});

		System.out.println("--------------");

		// 事前にJpqlSandbox01NamedQueryPrepareServiceで登録したもの
		List<Emp> emps2 = em.createNamedQuery("Emp.findByAfterCreatedAt", Emp.class)
				.setParameter("createdAt", Timestamp.valueOf(LocalDateTime.of(2023, 5, 21, 0, 0, 0)))
				.getResultList();
		emps2.forEach(e -> {
			System.out.println(e);
		});
	}
}
