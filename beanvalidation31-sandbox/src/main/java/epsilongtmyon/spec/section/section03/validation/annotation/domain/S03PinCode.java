package epsilongtmyon.spec.section.section03.validation.annotation.domain;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import epsilongtmyon.spec.section.section03.validation.annotation.S03HalfNum;
import epsilongtmyon.spec.section.section03.validation.annotation.S03MaxLength;


// ほかのアノテーションと組み合わせて検証アノテーションを作る
/**
 * PINコード
 */
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(S03PinCode.List.class)
@Constraint(validatedBy = {}) //@Constraint は必要だが、これ以上検証する必要はないので{}にする。 
//--
@S03HalfNum
@S03MaxLength(length = 4)
public @interface S03PinCode {

	//このメッセージは使われない(このアノテーション独自の追加検証があれば使われるだろうけど)
	String message() default "{epsilongtmyon.spec.section.section03.validation.annotation.S03PinCode.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		S03PinCode[] value();
	}
}
