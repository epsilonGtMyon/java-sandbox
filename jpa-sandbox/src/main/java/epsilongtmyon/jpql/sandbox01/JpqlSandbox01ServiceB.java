package epsilongtmyon.jpql.sandbox01;

import java.io.Serializable;
import java.util.List;

import epsilongtmyon.db.entity.OrderDetail;
import epsilongtmyon.db.entity.OrderHeader;
import epsilongtmyon.jpql.sandbox01.dto.JpqlSandbox01OrderTotalDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class JpqlSandbox01ServiceB implements Serializable {

	private final EntityManager em;

	public JpqlSandbox01ServiceB(EntityManager em) {
		super();
		this.em = em;
	}

	public void execute() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			executeInternal04();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	private void executeInternal01() {

		final String jpql1 = """
				SELECT
				   h
				 FROM
				   OrderHeader h
				 WHERE
				   h.orderId >= :orderId
								""";

		TypedQuery<OrderHeader> query1 = em.createQuery(jpql1, OrderHeader.class)
				.setParameter("orderId", 1);
		
		List<OrderHeader> orders1 = query1.getResultList();
		orders1.forEach(order -> {
			System.out.println(order);
		});
		
	}

	private void executeInternal02h() {

		final String jpql1 = """
				SELECT
				   h
				 FROM
				   OrderHeader h
				 INNER JOIN
				   h.orderDetails d
				 WHERE
				   h.orderId >= :orderId
								""";

		TypedQuery<OrderHeader> query1 = em.createQuery(jpql1, OrderHeader.class)
				.setParameter("orderId", 3);
		
		List<OrderHeader> ods1 = query1.getResultList();
		ods1.forEach(d -> {
			System.out.println(d);
		});
		

		System.out.println("-----------------------");

		final String jpql2 = """
				SELECT
				   h
				 FROM
				   OrderHeader h
				 LEFT OUTER JOIN
				   h.orderDetails d
				 WHERE
				   h.orderId >= :orderId
								""";

		TypedQuery<OrderHeader> query2 = em.createQuery(jpql2, OrderHeader.class)
				.setParameter("orderId", 3);
		
		List<OrderHeader> ods2= query2.getResultList();
		ods2.forEach(d -> {
			System.out.println(d);
		});


		System.out.println("-----------------------");

		// FETCH結合
		final String jpql3 = """
				SELECT
				   h
				 FROM
				   OrderHeader h
				 JOIN FETCH
				   h.orderDetails d
				 WHERE
				   h.orderId >= :orderId
								""";

		TypedQuery<OrderHeader> query3 = em.createQuery(jpql3, OrderHeader.class)
				.setParameter("orderId", 3);
		
		List<OrderHeader> ods3= query3.getResultList();
		ods3.forEach(d -> {
			System.out.println(d);
		});
		
	}


	private void executeInternal02d() {

		final String jpql1 = """
				SELECT
				   d
				 FROM
				   OrderHeader h
				 INNER JOIN
				   h.orderDetails d
				 WHERE
				   h.orderId >= :orderId
								""";

		TypedQuery<OrderDetail> query1 = em.createQuery(jpql1, OrderDetail.class)
				.setParameter("orderId", 3);
		
		List<OrderDetail> ods1 = query1.getResultList();
		ods1.forEach(d -> {
			System.out.println(d);
		});
		

		System.out.println("-----------------------");

		final String jpql2 = """
				SELECT
				   d
				 FROM
				   OrderHeader h
				 LEFT OUTER JOIN
				   h.orderDetails d
				 WHERE
				   h.orderId >= :orderId
								""";

		TypedQuery<OrderDetail> query2 = em.createQuery(jpql2, OrderDetail.class)
				.setParameter("orderId", 3);
		
		List<OrderDetail> ods2= query2.getResultList();
		ods2.forEach(d -> {
			System.out.println(d);
		});


		System.out.println("-----------------------");

		// FETCH結合
		final String jpql3 = """
				SELECT
				   d
				 FROM
				   OrderHeader h
				 JOIN FETCH
				   h.orderDetails d
				 WHERE
				   h.orderId >= :orderId
								""";

		TypedQuery<OrderDetail> query3 = em.createQuery(jpql3, OrderDetail.class)
				.setParameter("orderId", 3);
		
		List<OrderDetail> ods3= query3.getResultList();
		ods3.forEach(d -> {
			System.out.println(d);
		});
	}
	
	private void executeInternal03() {
		// joinの条件式を試しつつWHEREでLIKEも
		final String jpql1 = """
SELECT
  h
FROM
  OrderHeader h
JOIN FETCH
  OrderDetail d
ON h.orderId = d.orderDetailPk.orderId
WHERE
  d.productName LIKE :name escape '$'

				""";
	
		final String paramName = "%100$%%";

		TypedQuery<OrderHeader> query1 = em.createQuery(jpql1, OrderHeader.class)
				.setParameter("name", paramName);

		// OrderHeaderとdetailsは100%のやつがとれる
		List<OrderHeader> orders1= query1.getResultList();
		orders1.forEach(h -> {
			System.out.println(h);
		});
	}
	

	private void executeInternal04() {
		// 集約関数
		final String jpql1 ="""
SELECT
  NEW  epsilongtmyon.jpql.sandbox01.dto.JpqlSandbox01OrderTotalDto(h.orderId, SUM(d.totalAmount))
FROM
  OrderHeader h
INNER JOIN
  OrderDetail d
ON h.orderId = d.orderDetailPk.orderId
GROUP BY
  h.orderId
ORDER BY
  h.orderId
				""";
		

		TypedQuery<JpqlSandbox01OrderTotalDto> query1 = em.createQuery(jpql1, JpqlSandbox01OrderTotalDto.class);
		List<JpqlSandbox01OrderTotalDto> ordersTotals = query1.getResultList();
		ordersTotals.forEach(x -> {
			System.out.println(x);
		});

	}
}
