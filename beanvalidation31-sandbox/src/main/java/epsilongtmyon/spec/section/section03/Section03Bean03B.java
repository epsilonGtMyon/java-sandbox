package epsilongtmyon.spec.section.section03;

import jakarta.validation.Valid;

import epsilongtmyon.spec.section.section03.validation.annotation.S03SamePassword;
import epsilongtmyon.spec.section.section03.validation.validator.S03SamePasswordValidator.S03SamePasswordHolder;

public class Section03Bean03B {

	@Valid
	@S03SamePassword
	private Section03Bean03B2 bean;

	public Section03Bean03B2 getBean() {
		return bean;
	}

	public void setBean(Section03Bean03B2 bean) {
		this.bean = bean;
	}

	public static class Section03Bean03B2 implements S03SamePasswordHolder {

		private String password;

		private String confirmPassword;

		public Section03Bean03B2(String password, String confirmPassword) {
			super();
			this.password = password;
			this.confirmPassword = confirmPassword;
		}

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
}
