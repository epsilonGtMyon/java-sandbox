package epsilongtmyon.extension.parameter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

//@ExtendWith を使ってExtensionを登録する。
@ExtendWith({ MyEnvironmentExtension.class })
public class MyEnvironmentDemo1Test {

	// フィールドにセットしても動くみたい..
	// @ExtendWith({ MyEnvironmentExtension.class })
	// private MyEnv env2;

	@Test
	void test1(MyEnvironment env) throws Exception {
		System.out.println(env);
	}

	@Test
	void test2(MyEnvironment env) throws Exception {
		System.out.println(env);
	}
}
