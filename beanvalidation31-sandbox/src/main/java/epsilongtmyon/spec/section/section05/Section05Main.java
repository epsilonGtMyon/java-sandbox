package epsilongtmyon.spec.section.section05;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;

import epsilongtmyon.spec.section.section05.Section05Bean01.Section05Bean01A;
import epsilongtmyon.spec.section.section05.Section05Bean02.Section05G1;
import epsilongtmyon.spec.section.section05.Section05Bean02.Section05G2;
import epsilongtmyon.spec.section.section05.Section05Bean02.Section05Order;

public class Section05Main {

	public static void main(String[] args) {
		var main = new Section05Main();
		main.start2();
	}

	private void start1() {
		Validator validator = defaultValidator();

		var bean = new Section05Bean01();

		bean.setNestedValue(new Section05Bean01A("abcdef"));
		bean.setListValue(List.of(new Section05Bean01A("abcdef"), new Section05Bean01A("abcdefg")));
		bean.setOptionalValue(Optional.of(new Section05Bean01A("abcdef")));

		var result = validator.validate(bean);
		result.forEach(System.out::println);
	}

	private void start2() {
		Validator validator = defaultValidator();

		var bean = new Section05Bean02();

		System.out.println("none:");
		validator.validate(bean).forEach(System.out::println);

		System.out.println("Section05G1:");
		validator.validate(bean, Section05G1.class).forEach(System.out::println);

		System.out.println("Section05G2:");
		validator.validate(bean, Section05G2.class).forEach(System.out::println);

		// ----
		// 複合
		System.out.println("Default Section05G1:");
		validator.validate(bean, Default.class, Section05G1.class).forEach(System.out::println);

		System.out.println("Section05G1 Section05G2:");
		validator.validate(bean, Section05G1.class, Section05G2.class).forEach(System.out::println);

		// 検証順序指定
		// 最初にSection05G2が実行されて、それのエラーが返ってくる
		System.out.println("Section05Order:");
		validator.validate(bean, Section05Order.class).forEach(System.out::println);
	}

	private static Validator defaultValidator() {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator;
	}
}
