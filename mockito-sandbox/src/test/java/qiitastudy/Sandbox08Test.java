package qiitastudy;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class Sandbox08Test {

	@Test
	void test1() throws Exception {
		//staticメソッドのモック化

		try (MockedStatic<Hoge> mocked = Mockito.mockStatic(Hoge.class)) {

			mocked.when(() -> Hoge.hello("test")).thenReturn("mocked");

			System.out.println("Hoge.hello() = " + Hoge.hello("test"));

			// verifyはMockedStaticに対して実行
			mocked.verify(() -> Hoge.hello("test"));
		}
	}
	

	@Test
	void test2() throws Exception {
		//staticメソッドのモック化

		try (MockedStatic<Foo> mocked = Mockito.mockStatic(Foo.class)) {

			mocked.when(() -> Foo.hello("test")).thenReturn("mocked Foo");

			System.out.println("Hoge.helloFoo() = " + Hoge.helloFoo("test"));

			mocked.verify(() -> Hoge.helloFoo("test"));
		}
	}
	

	public static class Hoge {
		public static String hello(String arg) {
			return "world (arg=" + arg + ")";
		}

		public static String helloFoo(String arg) {
			return Foo.hello(arg);
		}
	}

	public static class Foo {
		public static String hello(String arg) {
			return "hello foo " + arg;
		}
	}
}
