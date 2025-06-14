package epsilongtmyon.spec.common.validation.validator;

import java.util.Collection;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import epsilongtmyon.spec.common.validation.annotation.Required;

public class RequiredValidator implements ConstraintValidator<Required, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}

		if (value instanceof CharSequence cs) {
			return !cs.isEmpty();
		}

		if (value instanceof Collection<?> coll) {
			return !coll.isEmpty();
		}

		return true;
	}

}
