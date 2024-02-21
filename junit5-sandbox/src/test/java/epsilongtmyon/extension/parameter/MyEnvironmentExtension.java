package epsilongtmyon.extension.parameter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

// ParameterResolverの実装を作ることでテストメソッドのパラメータを注入することができるようになる。

public class MyEnvironmentExtension implements ParameterResolver {

	public MyEnvironmentExtension() {
		super();
		System.out.println("new MyEnvironmentExtension");
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		// 引数がサポート対象化
		return parameterContext.getParameter().getType() == MyEnvironment.class;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		// サポートしているときはその値を返す
		return new MyEnvironment();
	}

}
