package epsilongtmyon.spec.section.section06;

import epsilongtmyon.spec.common.validation.annotation.MaxLength;
import epsilongtmyon.spec.common.validation.annotation.Required;

public class Section06Bean03 {

	@Required
	private String code01;

	@MaxLength(length = 3)
	private String code02;

	public String getCode01() {
		return code01;
	}

	public void setCode01(String code01) {
		this.code01 = code01;
	}

	public String getCode02() {
		return code02;
	}

	public void setCode02(String code02) {
		this.code02 = code02;
	}

}
