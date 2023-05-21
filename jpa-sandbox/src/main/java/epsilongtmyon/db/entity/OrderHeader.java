package epsilongtmyon.db.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDER_HEADER")
public class OrderHeader implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private BigInteger orderId;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_ID")
	private List<OrderDetail> orderDetails;

	public BigInteger getOrderId() {
		return orderId;
	}

	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "OrderHeader [orderId=" + orderId + ", customerName=" + customerName + ", orderDetails=" + orderDetails
				+ "]";
	}

}
