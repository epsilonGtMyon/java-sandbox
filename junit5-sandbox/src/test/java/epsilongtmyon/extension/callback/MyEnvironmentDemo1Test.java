package epsilongtmyon.extension.callback;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ MyEnvironmentExtension.class })
public class MyEnvironmentDemo1Test {

	@MyEnv("JAVA_HOME")
	private static String env1;

	@MyEnv("APPDATA")
	private String env2;

	@Test
	void test1(TestInfo testInfo) throws Exception {
		System.out.println(testInfo.getDisplayName());
		System.out.println(env1);
		System.out.println(env2);
	}

	@Test
	void test2(TestInfo testInfo) throws Exception {
		System.out.println(testInfo.getDisplayName());
		System.out.println(env1);
		System.out.println(env2);
	}
}
