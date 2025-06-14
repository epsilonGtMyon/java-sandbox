package epsilongtmyon.spec.common.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import epsilongtmyon.spec.common.validation.annotation.MaxLength;

public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {

	private int length;

	@Override
	public void initialize(MaxLength constraintAnnotation) {
		this.length = constraintAnnotation.length();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return true;
		}

		return value.length() <= length;
	}

}
