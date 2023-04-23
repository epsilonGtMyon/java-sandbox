package qiitastudy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class Sandbox03Test {

	@Test
	void test1() throws Exception {
		List<String> mock = Mockito.mock(List.class);
		mock.get(1);

		// fail
		// Mockito.verify(mock).get(0);

		Mockito.verify(mock).get(1);

	}

	@Test
	void test2() {
		List<String> mock = Mockito.mock(List.class);

		mock.add("one");
		mock.add("two");
		mock.add("three");

		// 渡された引数の検証
		// 1回でも呼ばれてたらOK
		Mockito.verify(mock).add(Mockito.startsWith("th")); // テストOK
	}

	@Test
	void test3() throws Exception {
		List<String> mock = Mockito.mock(List.class);

		mock.get(0);
		mock.get(0);
		mock.get(0);

		// 回数の検証
		// fail
		// Mockito.verify(mock, Mockito.times(2)).get(0);

		Mockito.verify(mock, Mockito.times(3)).get(0);

		// 最低回数
		// fail
		// Mockito.verify(mock, Mockito.atLeast(4)).get(0);
		Mockito.verify(mock, Mockito.atLeast(2)).get(0);

		// 最高回数
		// fail
		// Mockito.verify(mock, Mockito.atMost(2)).get(0);
		Mockito.verify(mock, Mockito.atMost(4)).get(0);

		// 呼び出されないこと
		// fail
		// Mockito.verify(mock, Mockito.never()).get(0);
		Mockito.verify(mock, Mockito.never()).get(1);
	}

	@Test
	void test4() throws Exception {
		List<String> mock = Mockito.mock(List.class);

		mock.get(0);
		mock.get(1);
		mock.get(2);

		// 順番の検証
		InOrder inOrder = Mockito.inOrder(mock);

		inOrder.verify(mock).get(0);
		inOrder.verify(mock).get(1);
		inOrder.verify(mock).get(2);
	}

	@Test
	void test5() throws Exception {
		List<String> mock1 = Mockito.mock(List.class);
		List<String> mock2 = Mockito.mock(List.class);

		mock1.get(0);
		mock1.get(1);
		mock2.get(2);

		// 複数モックに対する順番の保障
		InOrder inOrder = Mockito.inOrder(mock1, mock2);

		// fail
		//		inOrder.verify(mock1).get(0);
		//		inOrder.verify(mock2).get(2);
		//		inOrder.verify(mock1).get(1);

		inOrder.verify(mock1).get(0);
		inOrder.verify(mock1).get(1);
		inOrder.verify(mock2).get(2);
	}
	
	@Test
	void test6() throws Exception {
		List<String> mock = Mockito.mock(List.class);
		
		mock.get(0);
		mock.get(1);
		mock.get(9);
		
		ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
		Mockito.verify(mock, Mockito.times(3)).get(captor.capture());

		// 最後のキャプチャした値
        System.out.println("captor.getValue()     = " + captor.getValue());
        // 全てのキャプチャした値
        System.out.println("captor.getAllValues() = " + captor.getAllValues());
		
	}

}
