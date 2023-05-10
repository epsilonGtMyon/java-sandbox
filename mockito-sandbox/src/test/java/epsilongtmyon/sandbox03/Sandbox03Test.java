package epsilongtmyon.sandbox03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

class Sandbox03Test {

	//---------------------------
	// nullを含まない

	@Test
	@DisplayName("anyStringはnullは含まない")
	public void notMatchNull1() throws Exception {

		Sandbox03 mock = Mockito.mock(Sandbox03.class);
		Mockito.when(mock.hello(Mockito.anyString()))
				.thenAnswer(AdditionalAnswers.answer(x -> String.format("mock %s", x)));

		assertNull(mock.hello(null));
		assertEquals("mock Taro", mock.hello("Taro"));
	}

	@Test
	@DisplayName("Mockito.any(String.class)はnullは含まない")
	public void notMatchNull2() throws Exception {

		Sandbox03 mock = Mockito.mock(Sandbox03.class);
		Mockito.when(mock.hello(Mockito.any(String.class)))
				.thenAnswer(AdditionalAnswers.answer(x -> String.format("mock %s", x)));

		assertNull(mock.hello(null));
		assertEquals("mock Taro", mock.hello("Taro"));
	}

	@Test
	@DisplayName("Mockito.isA(String.class)はnullは含まない")
	public void notMatchNull3() throws Exception {

		Sandbox03 mock = Mockito.mock(Sandbox03.class);
		Mockito.when(mock.hello(Mockito.isA(String.class)))
				.thenAnswer(AdditionalAnswers.answer(x -> String.format("mock %s", x)));

		assertNull(mock.hello(null));
		assertEquals("mock Taro", mock.hello("Taro"));
	}

	//---------------------------
	// nullを含む

	@Test
	@DisplayName("(String) Mockito.any()はnullを含む")
	public void matchNull1() throws Exception {

		Sandbox03 mock = Mockito.mock(Sandbox03.class);
		Mockito.when(mock.hello((String) Mockito.any()))
				.thenAnswer(AdditionalAnswers.answer(x -> String.format("mock %s", x)));

		assertEquals("mock null", mock.hello(null));
		assertEquals("mock Taro", mock.hello("Taro"));
	}

	@Test
	@DisplayName("Mockito.nullable(String.class)はnullを含む")
	public void matchNull2() throws Exception {

		Sandbox03 mock = Mockito.mock(Sandbox03.class);
		Mockito.when(mock.hello(Mockito.nullable(String.class)))
				.thenAnswer(AdditionalAnswers.answer(x -> String.format("mock %s", x)));

		assertEquals("mock null", mock.hello(null));
		assertEquals("mock Taro", mock.hello("Taro"));
	}
}
