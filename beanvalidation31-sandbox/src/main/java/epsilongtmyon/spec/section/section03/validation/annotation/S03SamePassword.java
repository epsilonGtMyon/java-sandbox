package epsilongtmyon.spec.section.section03.validation.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import epsilongtmyon.spec.section.section03.validation.validator.S03SamePasswordValidator;

/**
 * 確認パスワードチェック
 */
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(S03SamePassword.List.class)
@Constraint(validatedBy = S03SamePasswordValidator.class)
public @interface S03SamePassword {

	String message() default "{epsilongtmyon.spec.section.section03.validation.annotation.S03SamePassword.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		S03SamePassword[] value();
	}
}
