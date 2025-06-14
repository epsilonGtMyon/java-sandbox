package epsilongtmyon.spec.section.section03;

import epsilongtmyon.spec.section.section03.validation.annotation.S03HalfNum;
import epsilongtmyon.spec.section.section03.validation.annotation.S03MaxLength;

public class Section03Bean01 {

	@S03HalfNum
	@S03MaxLength(length = 3)
	private String strValue01;

	private String strValue02;

	public String getStrValue01() {
		return strValue01;
	}

	public void setStrValue01(String strValue01) {
		this.strValue01 = strValue01;
	}

	public String getStrValue02() {
		return strValue02;
	}

	public void setStrValue02(String strValue02) {
		this.strValue02 = strValue02;
	}

}
