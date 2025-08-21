package epsilongtmyon.common.db.entity;

import java.math.BigDecimal;

public class MyException extends AbstractEntity {

	private String exKey;

	private BigDecimal amount;

	public String getExKey() {
		return exKey;
	}

	public void setExKey(String exKey) {
		this.exKey = exKey;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "MyException [exKey=" + exKey + ", amount=" + amount + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
