package epsilongtmyon.basic.parameterized;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import epsilongtmyon.Calculator;

public class MethodSourceTest {

	private Calculator calc = new Calculator();

	//----------------------------------
	// MethodSourceを使うことでパラメータを外部メソッド化できる
	// MethodSourceのvalueを省略したときは、テストメソッドと同じ名前のものを探してくる
	// 別のクラスのstaticメソッドを指定することもできる。

	@ParameterizedTest
	@MethodSource
	void testIsLengthGt2(String value) {
		assertTrue(value.length() > 2);
	}

	// パラメータを提供するメソッド
	// テストクラスのライフサイクルがPER_CLASSじゃない限りstaticメソッドに
	// Streamを返すのが基本だが、ListなどStream化可能なものでもいける
	static Stream<String> testIsLengthGt2() {
		return Stream.of("003", "0004");
	}

	//----------------------------------
	// 複数の引数
	// Argumentsを使うことで複数の引数に対応

	@ParameterizedTest
	@MethodSource("calculatorAddTestSource")
	void testAdd01(int left, int right, int result) {
		assertEquals(result, calc.add(left, right));
	}

	@ParameterizedTest
	@MethodSource("calculatorAddTestSource")
	void testAdd02(ArgumentsAccessor accessor) {
		Integer left = accessor.getInteger(0);
		Integer right = accessor.getInteger(1);
		Integer result = accessor.getInteger(2);
		
		assertEquals(result, calc.add(left, right));
	}

	static Stream<Arguments> calculatorAddTestSource() {
		return Stream.of(
				Arguments.arguments(1, 2, 3),
				Arguments.arguments(2, 3, 5),
				Arguments.arguments(3, 4, 7));
	}
}
