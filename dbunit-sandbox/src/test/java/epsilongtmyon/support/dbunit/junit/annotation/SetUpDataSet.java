package epsilongtmyon.support.dbunit.junit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// とりあえずExcelだけ

/**
 * データ投入のデータセットを指定するアノテーション
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SetUpDataSet {

	String fullPath() default "";

	String resourceName() default "";

}
