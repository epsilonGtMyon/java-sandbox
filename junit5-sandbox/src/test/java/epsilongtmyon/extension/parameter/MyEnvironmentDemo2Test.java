package epsilongtmyon.extension.parameter;

import org.junit.jupiter.api.Test;

@MyEnv
public class MyEnvironmentDemo2Test {

	@Test
	void test1(MyEnvironment env) throws Exception {
		System.out.println(env);
	}

	@Test
	void test2(MyEnvironment env) throws Exception {
		System.out.println(env);
	}
}
