package epsilongtmyon.spec.section.section03;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.metadata.ConstraintDescriptor;

import epsilongtmyon.spec.section.section03.Section03Bean03B.Section03Bean03B2;

public class Section03Main {

	public static void main(String[] args) {
		var main = new Section03Main();
		System.out.println("=======1");
		main.start1();
		System.out.println("=======2");
		main.start2();
		System.out.println("=======3A");
		main.start3A();
		System.out.println("=======3B");
		main.start3B();
	}

	private void start1() {
		Validator validator = defaultValidator();

		Section03Bean01 bean = new Section03Bean01();
		bean.setStrValue01("abcd1234");

		var result = validator.validate(bean);
		result.forEach(System.out::println);

	}

	// 合成アノテーションの検証
	private void start2() {
		Validator validator = defaultValidator();

		Section03Bean02 bean = new Section03Bean02();
		validator.validate(bean).forEach(System.out::println);

		// @PinCode2側はメッセージが上書きされている。
		System.out.println("--------------");
		bean.setPinCode1("abcde");
		bean.setPinCode2("abcde");
		bean.setPinCode3("abcde");
		Set<ConstraintViolation<Section03Bean02>> results = validator.validate(bean);
		for(var r : results) {
			
			System.out.println();
			System.out.println(r.getPropertyPath());
			System.out.println(r.getInvalidValue());
			System.out.println(r.getMessage());
			System.out.println(r.getMessageTemplate());
			System.out.println(r.getRootBean());
			System.out.println(r.getRootBeanClass());
			System.out.println(r.getLeafBean());
			
			ConstraintDescriptor<?> d = r.getConstraintDescriptor();
			System.out.println(d);
			System.out.println(d.getAnnotation());
			System.out.println(d.isReportAsSingleViolation());
			System.out.println(d.getComposingConstraints());//PinCode3だけ入ってるReportAsSingleViolation=trueだから？
			System.out.println(d.getAttributes());
		}
	}

	// クラスに対する検証でアノテーションをクラスに付与
	private void start3A() {
		Validator validator = defaultValidator();

		Section03Bean03A bean = new Section03Bean03A();
		validator.validate(bean).forEach(System.out::println);
		System.out.println("--------------");

		bean.setPassword("abc");
		bean.setPassword("def");
		validator.validate(bean).forEach(System.out::println);
	}

	// クラスに対する検証でアノテーションをフィールドにValidとともに付与
	private void start3B() {
		Validator validator = defaultValidator();

		Section03Bean03B bean = new Section03Bean03B();
		validator.validate(bean).forEach(System.out::println);
		System.out.println("--------------");

		bean.setBean(new Section03Bean03B2("abc", "def"));
		validator.validate(bean).forEach(System.out::println);
	}

	private static Validator defaultValidator() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		// カスタマイズもできる
		//		Validation.byDefaultProvider()
		//		.configure()
		// @Pastのような日付の前後比較に使うClockのプロバーダーも指定できる。
		// ConstraintValidatorContext#getClockProviderで取得できる。
		//		.clockProvider(DefaultClockProvider.INSTANCE)
		//		.buildValidatorFactory();

		return validator;
	}
}
