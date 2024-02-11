package epsilongtmyon.basic.parameterized;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import epsilongtmyon.Calculator;

public class CsvFileSourceTest {

	private Calculator calc = new Calculator();

	@ParameterizedTest
	@CsvFileSource(resources = "/META-INF/epsilongtmyon/basic/parameterized/CsvFileSourceTest/file01.csv", useHeadersInDisplayName = true)
	void testAdd01(int left, int right, int result) {
		assertEquals(result, calc.add(left, right));
	}
}
