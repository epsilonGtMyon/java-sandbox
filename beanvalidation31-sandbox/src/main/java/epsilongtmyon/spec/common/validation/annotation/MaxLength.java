package epsilongtmyon.spec.common.validation.annotation;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import epsilongtmyon.spec.common.validation.validator.MaxLengthValidator;


/**
 * 最大文字数
 */
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(MaxLength.List.class)
@Constraint(validatedBy = MaxLengthValidator.class)
public @interface MaxLength {

	String message() default "{epsilongtmyon.spec.common.validation.annotation.MaxLength.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	int length();
	

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		MaxLength[] value();
	}
}
