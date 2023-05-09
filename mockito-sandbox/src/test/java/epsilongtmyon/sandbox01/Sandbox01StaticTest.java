package epsilongtmyon.sandbox01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class Sandbox01StaticTest {

	@Test
	@DisplayName("mockStaticしたときに動作を定義してないメソッドの挙動を確認")
	public void test1() throws Exception {

		try (MockedStatic<Sandbox01Static> mockStatic = Mockito.mockStatic(Sandbox01Static.class)) {

			mockStatic.when(() -> Sandbox01Static.repeat2(Mockito.anyString())).thenReturn("hello");

			// 本来の処理を行う
			mockStatic.when(() -> Sandbox01Static.repeat4(Mockito.anyString())).thenCallRealMethod();

			// モック処理
			System.out.println(Sandbox01Static.repeat2("abc"));
			// nullが返ってくる
			System.out.println(Sandbox01Static.repeat3("abc"));
			// 本来の処理なのでabc4回分
			System.out.println(Sandbox01Static.repeat4("abc"));
		}
	}

	@Test
	@DisplayName("mockStaticしたときにprotectedメソッドの挙動も変更できるのか")
	public void test2() throws Exception {
		try (MockedStatic<Sandbox01Static> mockStatic = Mockito.mockStatic(Sandbox01Static.class)) {

			mockStatic.when(() -> Sandbox01Static.protectedHello(Mockito.anyString())).thenAnswer(
					AdditionalAnswers.answer(s -> String.format("protected %s", s)));

			// protectedを呼び出しているpublicは本来の処理を行う
			mockStatic.when(() -> Sandbox01Static.publicHello(Mockito.anyString())).thenCallRealMethod();

			System.out.println(Sandbox01Static.publicHello("yahoo"));
		}
	}

}
