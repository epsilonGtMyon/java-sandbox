package epsilongtmyon.service.sample.db.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TestTable02 {

	private String key1;

	private String key2;

	private String varcharCol;

	private BigDecimal numberCol;

	private Timestamp timestampCol;

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getVarcharCol() {
		return varcharCol;
	}

	public void setVarcharCol(String varcharCol) {
		this.varcharCol = varcharCol;
	}

	public BigDecimal getNumberCol() {
		return numberCol;
	}

	public void setNumberCol(BigDecimal numberCol) {
		this.numberCol = numberCol;
	}

	public Timestamp getTimestampCol() {
		return timestampCol;
	}

	public void setTimestampCol(Timestamp timestampCol) {
		this.timestampCol = timestampCol;
	}

}
