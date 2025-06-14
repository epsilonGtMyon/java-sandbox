package epsilongtmyon.spec.section.section03.validation.validator;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import epsilongtmyon.spec.section.section03.validation.annotation.S03HalfNum;

public class S03HalfNumValidator implements ConstraintValidator<S03HalfNum, String> {

	private static final Pattern pattern = Pattern.compile("^[0-9]+$");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return true;
		}

		return pattern.matcher(value).matches();
	}

}
