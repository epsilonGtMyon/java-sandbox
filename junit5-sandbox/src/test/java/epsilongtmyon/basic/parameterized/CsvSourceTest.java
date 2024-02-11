package epsilongtmyon.basic.parameterized;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import epsilongtmyon.Calculator;

public class CsvSourceTest {

	private Calculator calc = new Calculator();

	@ParameterizedTest
	@CsvSource({
			"1,    2,   3",
			"2,    3,   5",
			"3,    4,   7"
	})
	void testAdd01(int left, int right, int result) {
		assertEquals(result, calc.add(left, right));
	}
	
	//テキストブロックを使う
	@ParameterizedTest
	@CsvSource(textBlock = """
			1,    2,   3
			2,    3,   5
			3,    4,   7
			""")
	void testAdd02(int left, int right, int result) {
		assertEquals(result, calc.add(left, right));
	}

	// useHeadersInDisplayNameを使った例
	@ParameterizedTest(name = "[{index}] {arguments}")
	@CsvSource(useHeadersInDisplayName = true, textBlock = """
			a,    b,   c
			1,    2,   3
			2,    3,   5
			3,    4,   7
			""")
	void testAdd03(int left, int right, int result) {
		assertEquals(result, calc.add(left, right));
	}
}
