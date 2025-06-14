package epsilongtmyon.spec.section.section06;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.ConstraintDescriptor;

import epsilongtmyon.spec.section.section06.Section06Bean01.Section06Method02Arg;
import epsilongtmyon.spec.section.section06.Section06Bean02.Section06Bean02A;
import epsilongtmyon.spec.section.section06.Section06Bean02.Section06Bean02B;

public class Section06Main {

	public static void main(String[] args) throws ReflectiveOperationException {
		var main = new Section06Main();
		System.out.println("=======1");
		main.start1();
		System.out.println("=======2");
		main.start2();
		System.out.println("=======3");
		main.start3();
		System.out.println("=======4");
		main.start4();
	}

	// メソッド引数の検証
	// リフレクションでメソッド呼び出すときに、ついでに使えそう
	// エラーの時のパラメータ名はParameterNameProvider によって提供されるらしい
	private void start1() throws ReflectiveOperationException {
		ExecutableValidator executableValidator = executableValidator();

		// 対象メソッド
		Method method = Section06Bean01.class.getMethod("method01", String.class, String.class);

		Section06Bean01 bean = new Section06Bean01();
		System.out.println("--------------");
		executableValidator.validateParameters(bean, method, new Object[] { null, null })
				.forEach(System.out::println);

		System.out.println("--------------");
		executableValidator.validateParameters(bean, method, new Object[] { "1", "2" })
				.forEach(System.out::println);
		System.out.println("--------------");
		executableValidator.validateParameters(bean, method, new Object[] { "12345", "abcdef" })
				.forEach(System.out::println);

	}

	private void start2() throws ReflectiveOperationException {
		ExecutableValidator executableValidator = executableValidator();

		// 対象メソッド
		Method method = Section06Bean01.class.getMethod("method02", Section06Method02Arg.class);

		Section06Bean01 bean = new Section06Bean01();
		System.out.println("--------------");
		executableValidator.validateParameters(bean, method, new Object[] { null })
				.forEach(System.out::println);

		System.out.println("--------------");
		executableValidator.validateParameters(bean, method, new Object[] { new Section06Method02Arg(null, "abcdefg") })
				.forEach(System.out::println);

		System.out.println("--------------");
		executableValidator.validateParameters(bean, method, new Object[] { new Section06Method02Arg("abc", "123") })
				.forEach(System.out::println);

	}

	private void start3() {
		Validator validator = defaultValidator();

		Section06Bean02 bean = new Section06Bean02();

		System.out.println("--------------");
		validator.validate(bean).forEach(System.out::println);

		System.out.println("--------------");
		bean.setValue01("xyza");
		bean.setNestedBeanA(new Section06Bean02A("1123456", "xxx"));
		bean.setListBeansB(List.of(
				new Section06Bean02B("12345", "34567"),
				new Section06Bean02B("abc", "def")));
		Set<ConstraintViolation<Section06Bean02>> results = validator.validate(bean);
		for (var r : results) {

			System.out.println();
			System.out.println(r.getPropertyPath());
			System.out.println("****");
			// NodeはIterableを実装してるのでforで階層を掘り下げることができる。
			for (Path.Node n : r.getPropertyPath()) {
				System.out.println("--");
				System.out.println(n.getName());
				System.out.println(n.getKind());
				System.out.println(n.isInIterable());//Listとかならtrue
				System.out.println(n.getIndex());// Listならindexが
				System.out.println(n.getKey());// Mapならとれるのかな
			}
			System.out.println("****");

			System.out.println(r.getInvalidValue());
			System.out.println(r.getMessage());
			System.out.println(r.getMessageTemplate());
			System.out.println(r.getRootBean());
			System.out.println(r.getRootBeanClass());
			System.out.println(r.getLeafBean());

			ConstraintDescriptor<?> d = r.getConstraintDescriptor();
			System.out.println(d);
			System.out.println(d.getAnnotation());
			System.out.println(d.getComposingConstraints());
			System.out.println(d.getAttributes());
		}

	}

	// usingContextを使って部分的にMessageInterpolatorを差し替える
	private void start4() {
		ValidatorFactory factory = defaultValidatorFactory();

		//configuration.getDefaultMessageInterpolator()のほうがいいかも
		MessageInterpolator orgMessageInterpolator = factory.getMessageInterpolator();

		Validator validator = factory.usingContext()
				.messageInterpolator(new Section06MessageInterpolator(orgMessageInterpolator))
				.getValidator();

		Section06Bean03 bean = new Section06Bean03();
		bean.setCode02("123456");

		validator.validate(bean).forEach(System.out::println);

	}

	// ---------------

	private static ValidatorFactory defaultValidatorFactory() {
		return Validation.buildDefaultValidatorFactory();
	}

	private static Validator defaultValidator() {
		Validator validator = defaultValidatorFactory().getValidator();
		return validator;
	}

	private static ExecutableValidator executableValidator() {
		Validator validator = defaultValidator();
		ExecutableValidator executableValidator = validator.forExecutables();
		return executableValidator;
	}
}
