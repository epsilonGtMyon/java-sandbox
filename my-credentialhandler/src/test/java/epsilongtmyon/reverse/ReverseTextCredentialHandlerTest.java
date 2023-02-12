package epsilongtmyon.reverse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ReverseTextCredentialHandlerTest {

	@Nested
	public class MutateTest {

		private ReverseTextCredentialHandler handler = new ReverseTextCredentialHandler();

		@Test
		void test() {
			String mutate = handler.mutate("123");

			assertEquals("321", mutate);
		}
	}

}
