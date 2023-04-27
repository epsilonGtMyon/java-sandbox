package qiitastudy;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Sandbox05Test {

	@Test
	void test1() throws Exception {
		
		List<String> mock1 = Mockito.mock(List.class);
		assertFalse(mock1 instanceof Serializable);

		// シリアライズできるようになるらしい
		// どう使うか知らんけど
		List<String> mock2 = Mockito.mock(List.class, Mockito.withSettings().serializable());
		assertTrue(mock2 instanceof Serializable);
	}
	
	@Test
	void test2() throws Exception {
		List<String> mock = Mockito.when(Mockito.mock(List.class).get(0)).thenReturn("mocked").getMock();
		
		System.out.println(mock.get(0));
	}
}
