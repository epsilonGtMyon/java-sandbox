package epsilongtmyon.spec.section.section03;

import epsilongtmyon.spec.section.section03.validation.annotation.S03SamePassword;
import epsilongtmyon.spec.section.section03.validation.validator.S03SamePasswordValidator.S03SamePasswordHolder;

@S03SamePassword
public class Section03Bean03A implements S03SamePasswordHolder {

	private String password;

	private String confirmPassword;

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
