package qiitastudy;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class Sandbox06Test {

	// アノテーションで定義することができる

	@Mock
	protected List<String> mock;

	@Spy
	protected List<String> spy = new ArrayList<>();

	@Captor
	protected ArgumentCaptor<String> captor;

	@BeforeEach
	void setUp() {
		// このメソッドを呼ばないとアノテーションの内容が有効にならない
		// closeしたほうがいいのか？
		AutoCloseable closable = MockitoAnnotations.openMocks(this);
	}

	@Test
	void test1() throws Exception {
		Mockito.when(mock.get(0)).thenReturn("mocked");
		System.out.println("mock.get(0) = " + mock.get(0));

		spy.add("hello");
		spy.add("world");
		Mockito.doReturn("spied").when(spy).get(0);
		System.out.println("spy.get(0) = " + spy.get(0));
		System.out.println("spy.get(1) = " + spy.get(1));

		verify(spy, atLeastOnce()).add(captor.capture());
		System.out.println("captor.getAllValues() = " + captor.getAllValues());
	}

}
