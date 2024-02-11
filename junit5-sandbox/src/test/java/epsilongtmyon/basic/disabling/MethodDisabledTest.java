package epsilongtmyon.basic.disabling;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class MethodDisabledTest {

	
	@Disabled("for test")
	@Test
	void test1() throws Exception {
		
	}

	@Test
	void test2() throws Exception {
		
	}

	@Test
	void test3() throws Exception {
		
	}
}
