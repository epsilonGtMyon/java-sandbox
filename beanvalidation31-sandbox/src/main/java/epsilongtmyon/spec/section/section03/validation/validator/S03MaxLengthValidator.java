package epsilongtmyon.spec.section.section03.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import epsilongtmyon.spec.section.section03.validation.annotation.S03MaxLength;

public class S03MaxLengthValidator implements ConstraintValidator<S03MaxLength, String> {

	private int length;

	@Override
	public void initialize(S03MaxLength constraintAnnotation) {
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
