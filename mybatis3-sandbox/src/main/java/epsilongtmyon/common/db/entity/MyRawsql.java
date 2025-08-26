package epsilongtmyon.common.db.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MyRawsql extends AbstractEntity {

	private BigInteger id;
	private String col1;
	private BigDecimal col2;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public BigDecimal getCol2() {
		return col2;
	}

	public void setCol2(BigDecimal col2) {
		this.col2 = col2;
	}

	@Override
	public String toString() {
		return "MyRawsql [id=" + id + ", col1=" + col1 + ", col2=" + col2 + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
