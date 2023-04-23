package qiitastudy;

import static org.mockito.ArgumentMatchers.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Answer1;

public class Sandbox02Test {

	@Test
	void test1() throws Exception {
		BiFunction<String, String, String> mock = Mockito.mock(BiFunction.class);

		// anyなんたらみたいなあいまいなやつと組み合わせるときはこれじゃだめ
		// Mockito.when(mock.apply("aaa", anyString())).thenReturn("Mock!");

		// eqにしておかないと例外になる
		Mockito.when(mock.apply(eq("aaa"), anyString())).thenReturn("Mock!");

		System.out.println(mock.apply("aaa", "bbb"));

	}

	@Test
	void test2() throws Exception {
		Function<String, String> mock = Mockito.mock(Function.class);

		// 自作のArgumentMatcher
		ArgumentMatcher<String> isHoge = arg -> Objects.equals(arg, "hoge");

		// argThatを使うことで自作のArgumentMatcherを使うことができる
		Mockito.when(mock.apply(argThat(isHoge))).thenReturn("hogeがきたよ");

		System.out.println(mock.apply("hoge"));
		System.out.println(mock.apply("foo"));

	}

	//TODO
	// https://qiita.com/opengl-8080/items/dcdf2cb14f2642240a5b#%E8%87%AA%E4%BD%9C%E3%81%AE%E3%83%9E%E3%83%83%E3%83%81%E3%83%B3%E3%82%B0%E5%87%A6%E7%90%86%E3%82%92%E6%8C%87%E5%AE%9A%E3%81%99%E3%82%8B

	@Test
	void test3() throws Exception {
		Function<String, String> mock = Mockito.mock(Function.class);

		// モックの動きを自分で細かく制御
		Answer<String> answer1 = invocation -> {
			System.out.println("mock = " + invocation.getMock());
			System.out.println("method = " + invocation.getMethod());
			System.out.println("raw-arguments = " + Arrays.toString(invocation.getRawArguments()));
			System.out.println("arguments = " + Arrays.toString(invocation.getArguments()));
			return "answer1";
		};
		Answer<String> answer2 = invocation -> {
			return "answer2";
		};

		// その動きはthenAnswerで指定
		Mockito.when(mock.apply("hello"))
			.thenAnswer(answer1)
			.thenAnswer(answer2);

		// whenの条件の時だけanswerが実行、それ以外の時は実行されない
		System.out.println("mock.apply(hello) = " + mock.apply("hello"));
		System.out.println("mock.apply(hello) = " + mock.apply("hello"));
		System.out.println("mock.apply(hello) = " + mock.apply("hello"));
		System.out.println("mock.apply(hey)   = " + mock.apply("hey"));
	}
	

	@Test
	void test4() throws Exception {
		Function<String, String> mock = Mockito.mock(Function.class);
		
		// Answer1 ～ Answer6を使うことで引数を型安全にできる
		// 渡されるのはInvocationOnMockではなくてジェネリクスの型
		Answer1<String, String> answer = argString -> {
			return "return " + argString;
		};

		Mockito.when(mock.apply("hello"))
			//AdditionalAnswers.answerを使うことでthenAnswerに渡せる
			.thenAnswer(AdditionalAnswers.answer(answer));

		System.out.println("mock.apply(hello) = " + mock.apply("hello"));
		System.out.println("mock.apply(hello) = " + mock.apply("hello"));
		System.out.println("mock.apply(hey)   = " + mock.apply("hey"));
	}
	
	@Test
	void test5() throws Exception {
		Runnable mock = Mockito.mock(Runnable.class);
		
		// ↓戻り値がvoidだとこのように書くことはできない
		// Mockito.when(mock.run())
		
		// なのでこんな感じで記述する
		Mockito.doThrow(new RuntimeException("test")).when(mock).run();

        try {
            mock.run();
        } catch (Throwable e) {
            System.out.println("e = " + e);
        }
	}
}
