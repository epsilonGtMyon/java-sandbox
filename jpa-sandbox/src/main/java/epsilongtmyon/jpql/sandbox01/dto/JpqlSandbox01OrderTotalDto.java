package epsilongtmyon.jpql.sandbox01.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JpqlSandbox01OrderTotalDto implements Serializable {

	private final BigInteger orderId;

	private final BigDecimal orderTotalAmount;

	public JpqlSandbox01OrderTotalDto(BigInteger orderId, BigDecimal orderTotalAmount) {
		super();
		this.orderId = orderId;
		this.orderTotalAmount = orderTotalAmount;
	}

	public BigInteger getOrderId() {
		return orderId;
	}

	public BigDecimal getOrderTotalAmount() {
		return orderTotalAmount;
	}

	@Override
	public String toString() {
		return "JpqlSandbox01OrderTotalDto [orderId=" + orderId + ", orderTotalAmount=" + orderTotalAmount + "]";
	}

}
