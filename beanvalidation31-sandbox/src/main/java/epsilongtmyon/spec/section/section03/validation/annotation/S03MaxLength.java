package epsilongtmyon.spec.section.section03.validation.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import epsilongtmyon.spec.section.section03.validation.validator.S03MaxLengthValidator;

/**
 * 最大文字数
 */
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(S03MaxLength.List.class)
@Constraint(validatedBy = S03MaxLengthValidator.class)
public @interface S03MaxLength {

	String message() default "{epsilongtmyon.spec.section.section03.validation.annotation.S03MaxLength.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	int length();
	

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		S03MaxLength[] value();
	}
}
