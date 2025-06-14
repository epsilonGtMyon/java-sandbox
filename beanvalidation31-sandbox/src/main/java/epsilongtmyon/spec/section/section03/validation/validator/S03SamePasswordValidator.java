package epsilongtmyon.spec.section.section03.validation.validator;

import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import epsilongtmyon.spec.section.section03.validation.annotation.S03SamePassword;
import epsilongtmyon.spec.section.section03.validation.validator.S03SamePasswordValidator.S03SamePasswordHolder;

public class S03SamePasswordValidator implements ConstraintValidator<S03SamePassword, S03SamePasswordHolder> {

	@Override
	public boolean isValid(S03SamePasswordHolder value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		String password = value.getPassword();
		String confirmPassword = value.getConfirmPassword();
		if (isEmpty(password) && isEmpty(confirmPassword)) {
			return true;
		}

		return Objects.equals(password, confirmPassword);
	}

	private static boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	public static interface S03SamePasswordHolder {

		String getPassword();

		String getConfirmPassword();
	}
}
