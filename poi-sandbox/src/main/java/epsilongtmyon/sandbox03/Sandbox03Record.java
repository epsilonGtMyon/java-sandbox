package epsilongtmyon.sandbox03;

import java.math.BigDecimal;

public record Sandbox03Record(
		String productName,
		BigDecimal tanka,
		BigDecimal suryo) {

	public BigDecimal total() {
		return tanka.multiply(suryo);
	}

}
