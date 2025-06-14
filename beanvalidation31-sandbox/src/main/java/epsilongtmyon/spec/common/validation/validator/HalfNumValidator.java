package epsilongtmyon.spec.common.validation.validator;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import epsilongtmyon.spec.common.validation.annotation.HalfNum;

public class HalfNumValidator implements ConstraintValidator<HalfNum, String> {

	private static final Pattern pattern = Pattern.compile("^[0-9]+$");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return true;
		}

		return pattern.matcher(value).matches();
	}

}
