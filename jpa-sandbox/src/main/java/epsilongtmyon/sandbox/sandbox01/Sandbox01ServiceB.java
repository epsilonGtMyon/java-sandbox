package epsilongtmyon.sandbox.sandbox01;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import epsilongtmyon.db.entity.OrderDetail;
import epsilongtmyon.db.entity.OrderDetail.OrderDetailPk;
import epsilongtmyon.db.entity.OrderHeader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Sandbox01ServiceB {

	private final EntityManager em;

	public Sandbox01ServiceB(EntityManager em) {
		this.em = em;
	}

	public void execute() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			executeInternal01();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	private void executeInternal01() {

		final OrderHeader h = new OrderHeader();
		h.setCustomerName("お客様太郎");

		em.persist(h);
		em.flush();

		final BigInteger orderId = h.getOrderId();
		int orderDetailNo = 1;

		final OrderDetail detail1 = new OrderDetail();
		detail1.setOrderDetailPk(new OrderDetailPk(orderId, orderDetailNo++));
		detail1.setProductName("ポテチ コンソメ");
		detail1.setProductCount(1);
		detail1.setTotalAmount(new BigDecimal(150));
		em.persist(detail1);

		final OrderDetail detail2 = new OrderDetail();
		detail2.setOrderDetailPk(new OrderDetailPk(orderId, orderDetailNo++));
		detail2.setProductName("ポテチ うすしお");
		detail2.setProductCount(2);
		detail2.setTotalAmount(new BigDecimal(300));
		em.persist(detail2);

		final OrderDetail detail3 = new OrderDetail();
		detail3.setOrderDetailPk(new OrderDetailPk(orderId, orderDetailNo++));
		detail3.setProductName("果汁100%オレンジジュース");
		detail3.setProductCount(1);
		detail3.setTotalAmount(new BigDecimal(150));
		em.persist(detail3);
		


		final OrderDetail detail4 = new OrderDetail();
		detail4.setOrderDetailPk(new OrderDetailPk(orderId, orderDetailNo++));
		detail4.setProductName("100gポテト");
		detail4.setProductCount(1);
		detail4.setTotalAmount(new BigDecimal(150));
		em.persist(detail4);

	}

	private void executeInternal02() {

		final OrderHeader header = em.find(OrderHeader.class, new BigInteger("6"));
		System.out.println(header);

		final List<OrderDetail> details = header.getOrderDetails();
		for (OrderDetail detail : details) {
			System.out.println(detail);
		}

	}

}
