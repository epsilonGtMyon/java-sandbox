package epsilongtmyon.sandbox02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

class Sandbox02Test {

	@Test
	@DisplayName("mockConstructionしたときに動作を定義してないメソッドの挙動を確認")
	public void test1() throws Exception {

		try (MockedConstruction<Sandbox02> mockCtor = Mockito.mockConstruction(Sandbox02.class, (mock, ctx) -> {
			// モック
			Mockito.when(mock.repeat2(Mockito.nullable(String.class))).thenReturn("hello");
			// 本来の処理
			Mockito.when(mock.repeat4(Mockito.nullable(String.class))).thenCallRealMethod();
		})) {

			Sandbox02 s = new Sandbox02();

			// モックの処理
			assertEquals("hello", s.repeat2("hello"));
			// モック標準の挙動
			assertNull(s.repeat3("abc"));
			// 本来の処理
			assertEquals("abcabcabcabc", s.repeat4("abc"));
		}

	}

	@Test
	@DisplayName("mockConstructionしたときにprotectedメソッドの挙動も変更できるのか")
	public void test2() throws Exception {

		try (MockedConstruction<Sandbox02> mockCtor = Mockito.mockConstruction(Sandbox02.class, (mock, ctx) -> {
			// protectedの挙動を変更する。
			Mockito.when(mock.protectedHello(Mockito.nullable(String.class)))
					.thenAnswer(AdditionalAnswers.answer(s -> String.format("protected %s", s)));

			// publicは本来の挙動
			Mockito.when(mock.publicHello(Mockito.nullable(String.class))).thenCallRealMethod();
		})) {

			Sandbox02 s = new Sandbox02();

			// 挙動変更できる。
			assertEquals("protected yahoo", s.publicHello("yahoo"));
		}

	}

	@Test
	@DisplayName("mockConstructionしたときにprivateメソッドの挙動確認")
	public void test3() throws Exception {

		try (MockedConstruction<Sandbox02> mockCtor = Mockito.mockConstruction(Sandbox02.class, (mock, ctx) -> {
			// publicは本来の挙動
			Mockito.when(mock.publicHey(Mockito.nullable(String.class))).thenCallRealMethod();
		})) {

			Sandbox02 s = new Sandbox02();

			// privateメソッドは本来の挙動をしているようである
			assertEquals("hey yahoo", s.publicHey("yahoo"));
		}

	}

	@Test
	@DisplayName("mockConstructionしたときにMockito.CALLS_REAL_METHODSの挙動を確認")
	public void test4() throws Exception {

		try (MockedConstruction<Sandbox02> mockCtor = Mockito.mockConstruction(Sandbox02.class,
				Mockito.withSettings().defaultAnswer(Mockito.CALLS_REAL_METHODS), (mock, ctx) -> {

					Mockito.when(mock.repeat2(Mockito.nullable(String.class))).thenReturn("hello");

					// protectedの挙動を変更する。
					Mockito.when(mock.protectedHello(Mockito.nullable(String.class)))
							.thenAnswer(AdditionalAnswers.answer(s -> String.format("protected %s", s)));
				})) {

			Sandbox02 s = new Sandbox02();

			// repeat2だけモック化
			assertEquals("hello", s.repeat2("abc"));
			assertEquals("abcabcabc", s.repeat3("abc"));
			assertEquals("abcabcabcabc", s.repeat4("abc"));

			// publicHelloからモック化したprotectedHelloを呼んでいること
			assertEquals("protected yahoo", s.publicHello("yahoo"));
		}

	}

}
