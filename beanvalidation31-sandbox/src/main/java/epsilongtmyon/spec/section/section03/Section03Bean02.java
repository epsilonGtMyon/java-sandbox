package epsilongtmyon.spec.section.section03;

import epsilongtmyon.spec.section.section03.validation.annotation.domain.S03PinCode;
import epsilongtmyon.spec.section.section03.validation.annotation.domain.S03PinCode2;
import epsilongtmyon.spec.section.section03.validation.annotation.domain.S03PinCode3;

public class Section03Bean02 {

	@S03PinCode
	private String pinCode1;

	@S03PinCode2
	private String pinCode2;

	@S03PinCode3
	private String pinCode3;

	public String getPinCode1() {
		return pinCode1;
	}

	public void setPinCode1(String pinCode1) {
		this.pinCode1 = pinCode1;
	}

	public String getPinCode2() {
		return pinCode2;
	}

	public void setPinCode2(String pinCode2) {
		this.pinCode2 = pinCode2;
	}

	public String getPinCode3() {
		return pinCode3;
	}

	public void setPinCode3(String pinCode3) {
		this.pinCode3 = pinCode3;
	}

}
