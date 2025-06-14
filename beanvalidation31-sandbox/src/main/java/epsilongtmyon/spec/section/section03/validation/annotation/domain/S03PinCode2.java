package epsilongtmyon.spec.section.section03.validation.annotation.domain;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Payload;

import epsilongtmyon.spec.section.section03.validation.annotation.S03HalfNum;
import epsilongtmyon.spec.section.section03.validation.annotation.S03MaxLength;


/**
* PINコード
*/
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(S03PinCode2.List.class)
@Constraint(validatedBy = {}) 
//--
@S03HalfNum
@S03MaxLength(length = 4)
public @interface S03PinCode2 {

	// それぞれの検証でエラーが起きた時のメッセージをこれで上書きしている。
	// ただしこれだとabcdeといった「半角数値」「長さ」の両方に違反している場合同じメッセージが２つ出てしまう
	// なので@ReportAsSingleViolationを使ったほうがよさそう
    @OverridesAttribute(constraint = S03HalfNum.class, name = "message")
    @OverridesAttribute(constraint = S03MaxLength.class, name = "message")
	String message() default "{epsilongtmyon.spec.section.section03.validation.annotation.S03PinCode2.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		S03PinCode2[] value();
	}
}
