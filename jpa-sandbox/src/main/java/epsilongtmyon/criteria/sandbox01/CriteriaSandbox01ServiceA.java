package epsilongtmyon.criteria.sandbox01;

import java.util.UUID;

import epsilongtmyon.db.entity.Emp;
import epsilongtmyon.db.entity.OrderHeader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CriteriaSandbox01ServiceA {

	private final EntityManager em;

	public CriteriaSandbox01ServiceA(EntityManager em) {
		super();
		this.em = em;
	}

	public void execute() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			executeInternal05();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	private void executeInternal01() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);

		Root<Emp> root = cq.from(Emp.class);
		cq.select(root)
				.where(cb.equal(root.get("firstName"), "山本"));

		TypedQuery<Emp> query = em.createQuery(cq);

		query.getResultList().forEach(em -> {
			System.out.println(em);
		});

	}

	private void executeInternal02() {

		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<OrderHeader> cq = cb.createQuery(OrderHeader.class);

			Root<OrderHeader> root1 = cq.from(OrderHeader.class);

			cq.select(root1)
					.orderBy(cb.asc(root1.get("orderId")));

			TypedQuery<OrderHeader> query = em.createQuery(cq);
			query.getResultList().forEach(r -> {
				System.out.println(r);
			});
		}

		// Fetch結合は書き方が違うらしい
	}

	private void executeInternal03() {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
		Root<Emp> root = cq.from(Emp.class);

		//		Predicate where = cb.and(
		//				cb.equal(root.get("firstName"), "一郎"),
		//				cb.equal(root.get("familyName"), "山本"));

		// こういう書き方もできる
		// 動的に検索条件を追加するときはこの書き方の方がよい
		Predicate where = cb.conjunction();
		where = cb.and(where, cb.equal(root.get("firstName"), "一郎"));
		where = cb.and(where, cb.equal(root.get("familyName"), "山本"));

		cq.select(root)
				.where(where)
				.orderBy(cb.desc(root.get("empId")));

		TypedQuery<Emp> query = em.createQuery(cq);
		query.getResultList().forEach(r -> {
			System.out.println(r);
		});

	}

	private void executeInternal04() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Emp> update = cb.createCriteriaUpdate(Emp.class);

		Root<Emp> root = update.from(Emp.class);

		update.set(root.get("note"), "あいうえお" + UUID.randomUUID().toString())
				.where(cb.equal(root.get("empId"), "E0001"));

		int updated = em.createQuery(update)
				.executeUpdate();

		System.out.println("updated = %d".formatted(updated));
	}

	private void executeInternal05() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Emp> delete = cb.createCriteriaDelete(Emp.class);

		Root<Emp> root = delete.from(Emp.class);

		delete.where(cb.equal(root.get("empId"), "E0004"));

		int deleted = em.createQuery(delete)
				.executeUpdate();

		System.out.println("deleted = %d".formatted(deleted));
	}

}
