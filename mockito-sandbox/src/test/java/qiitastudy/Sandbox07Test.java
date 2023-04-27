package qiitastudy;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Sandbox07Test {

	// finalクラスもモックにできる。
	// mockito-inlineが必要

	@Test
	void test1() throws Exception {
		FinalClass mock = Mockito.mock(FinalClass.class);

		Mockito.when(mock.hello()).thenReturn("mocked");

		System.out.println(mock.hello());

	}

	public static final class FinalClass {
		public String hello() {
			return "world";
		}
	}
}
