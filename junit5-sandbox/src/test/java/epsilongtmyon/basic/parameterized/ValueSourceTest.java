package epsilongtmyon.basic.parameterized;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import epsilongtmyon.StringUtil;

public class ValueSourceTest {

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("nullと空文字列はtrue")
	void testIsEmpty(String value) {
		assertTrue(StringUtil.isEmpty(value));
	}

	@ParameterizedTest
	@ValueSource(strings = { " " })
	@NullAndEmptySource
	@DisplayName("nullと空文字列と空白はtrue")
	void testIsBlank(String value) {
		assertTrue(StringUtil.isBlank(value));
	}
}
