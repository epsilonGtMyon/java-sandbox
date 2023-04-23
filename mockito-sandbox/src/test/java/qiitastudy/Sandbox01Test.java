package qiitastudy;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class Sandbox01Test {
	
	@Test
	void test1() throws Exception {

		List<String> mock = Mockito.mock(List.class);
		
		// anyXxxを使うと任意の引数についてスタブ化できる
		// 挙動の定義は上書きになるので anyIntと 0 を逆にするとanyIntの方が優先される
		Mockito.when(mock.get(Mockito.anyInt())).thenReturn("yahoo!");
		Mockito.when(mock.get(0)).thenReturn("Hello World!");

		System.out.println(mock.get(0));
		System.out.println(mock.get(1));
		
	}
	
	@Test
	void test2() throws Exception {
		
		List<String> mock = Mockito.mock(List.class);

		Mockito.when(mock.get(0)).thenReturn("get 0");
		Mockito.when(mock.get(1)).thenReturn("get 1");
		
		System.out.println(mock.get(0));
		System.out.println(mock.get(0));
		System.out.println(mock.get(1));
		System.out.println(mock.get(2));
		
		
		// 書き換え

		Mockito.when(mock.get(0)).thenReturn("get 0+");
		Mockito.when(mock.get(1)).thenReturn("get 1+");

		System.out.println(mock.get(0));
		System.out.println(mock.get(0));
		System.out.println(mock.get(1));
		System.out.println(mock.get(2));
		
	}
	
	@Test
	void test3() throws Exception {
		List<String> mock = Mockito.mock(List.class);

		Mockito.when(mock.get(0)).thenReturn("get 0-1", "get 0-2", "get 0-3");

		// 実行する毎にかわるが 最後の結果が維持される
		System.out.println(mock.get(0));
		System.out.println(mock.get(0));
		System.out.println(mock.get(0));
		System.out.println(mock.get(0));
		System.out.println(mock.get(1));
		
	}
	
	@Test
	void test4() throws Exception {
		List<String> mock = Mockito.mock(List.class);
		
		Mockito.when(mock.get(0)).thenThrow(new RuntimeException("test exception"));
		
		try {
			mock.get(0);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		
	}

	
	@Test
	void test5() throws Exception {
		List<String> mock = Mockito.mock(List.class);
		
		// 実行する毎にかわるが、最後の例外が維持され続ける
		Mockito.when(mock.get(0))
		.thenThrow(
				new RuntimeException("test 0"),
				new IllegalArgumentException("test 1"),
				new IllegalStateException("test 2")
				)	;
		
		try {
			mock.get(0);
		} catch (RuntimeException e) {
			System.out.println(e);
		}

		try {
			mock.get(0);
		} catch (RuntimeException e) {
			System.out.println(e);
		}

		try {
			mock.get(0);
		} catch (RuntimeException e) {
			System.out.println(e);
		}

		try {
			mock.get(0);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		
	}

	
	@Test
	void test6() throws Exception {
		List<String> mock = Mockito.mock(List.class);

		// mockに対してwhen-thenを書いたときは挙動を上書きできるが
		// メソッドチェーンで書いた場合は 呼ばれるたびに異なる結果を返すことができる
		// 複数回呼んだ場合は最後のものが維持される
        when(mock.get(0))
            .thenReturn("test 1")
            .thenThrow(new RuntimeException("test 2"))
            .thenReturn("test 3");
		
        System.out.println(mock.get(0));

		try {
			mock.get(0);
		} catch (RuntimeException e) {
			System.out.println(e);
		}

        System.out.println(mock.get(0));

        System.out.println(mock.get(0));

	}
}
