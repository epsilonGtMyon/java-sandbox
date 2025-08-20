package epsilongtmyon.common.db.entity;

import java.math.BigInteger;

public class Series {

	private BigInteger seq;

	private String text;

	public BigInteger getSeq() {
		return seq;
	}

	public void setSeq(BigInteger seq) {
		this.seq = seq;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Series [seq=" + seq + ", text=" + text + "]";
	}

}
