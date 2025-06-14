package epsilongtmyon.spec.section.section03.validation.annotation.domain;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import epsilongtmyon.spec.section.section03.validation.annotation.S03HalfNum;
import epsilongtmyon.spec.section.section03.validation.annotation.S03MaxLength;


/**
* PINコード
*/
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(S03PinCode3.List.class)
@Constraint(validatedBy = {})
@ReportAsSingleViolation // このアノテーションを付けた場合は、このアノテーションのmessageが使われる。
//--
@S03HalfNum
@S03MaxLength(length = 4)
public @interface S03PinCode3 {

	String message() default "{epsilongtmyon.spec.section.section03.validation.annotation.S03PinCode3.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		S03PinCode3[] value();
	}
}
