package epsilongtmyon.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDER_DETAIL")
public class OrderDetail implements Serializable {

	@EmbeddedId
	private OrderDetailPk orderDetailPk;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_COUNT")
	private Integer productCount;

	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	public OrderDetailPk getOrderDetailPk() {
		return orderDetailPk;
	}

	public void setOrderDetailPk(OrderDetailPk orderDetailPk) {
		this.orderDetailPk = orderDetailPk;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailPk=" + orderDetailPk + ", productName=" + productName + ", productCount="
				+ productCount + ", totalAmount=" + totalAmount + "]";
	}

	//------------------------------

	@Embeddable
	public static class OrderDetailPk implements Serializable {

		@Column(name = "ORDER_ID")
		private BigInteger orderId;

		@Column(name = "ORDER_DETAIL_NO")
		private Integer orderDetailNo;

		public OrderDetailPk() {
		}

		public OrderDetailPk(BigInteger orderId, Integer orderDetailNo) {
			this.orderId = orderId;
			this.orderDetailNo = orderDetailNo;
		}

		public BigInteger getOrderId() {
			return orderId;
		}

		public void setOrderId(BigInteger orderId) {
			this.orderId = orderId;
		}

		public Integer getOrderDetailNo() {
			return orderDetailNo;
		}

		public void setOrderDetailNo(Integer orderDetailNo) {
			this.orderDetailNo = orderDetailNo;
		}

		@Override
		public String toString() {
			return "OrderDetailPk [orderId=" + orderId + ", orderDetailNo=" + orderDetailNo + "]";
		}
	}
}
