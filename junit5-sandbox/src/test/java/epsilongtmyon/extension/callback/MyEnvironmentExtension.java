package epsilongtmyon.extension.callback;

import java.lang.reflect.Field;
import java.util.function.Predicate;
import java.util.logging.Logger;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.support.AnnotationSupport;
import org.junit.platform.commons.support.ModifierSupport;

// BeforeAllCallback, BeforeEachCallbackの実装を作ることでテストの実行前のコールバックを作ることができる。
// ここでは MyEnvアノテーションがつけられたフィールドに環境変数の値を注入するExtensionを作成した
// あとついでにParameterResolverも

public class MyEnvironmentExtension implements BeforeAllCallback, BeforeEachCallback, ParameterResolver {

	private static Logger logger = Logger.getLogger(MyEnvironmentExtension.class.getName());

	// beforeAllではstaticフィールドへの注入を行う
	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		logger.info("beforeAll:" + context.getDisplayName());

		Class<?> testClass = context.getRequiredTestClass();
		// staticフィールドに注入したいので第2引数はインスタンスを渡す必要はない
		injectFields(testClass, null, ModifierSupport::isStatic);
	}

	// beforeEachではインスタンスフィールドへの注入を行う
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		logger.info("beforeEach:" + context.getDisplayName());

		Class<?> testClass = context.getRequiredTestClass();
		Object testInstance = context.getRequiredTestInstance();
		injectFields(testClass, testInstance, ModifierSupport::isNotStatic);
	}

	// フィールドへの注入
	private void injectFields(Class<?> testClass, Object testInstance,
			Predicate<Field> predicate) {

		// junitにはAnnotationSupportというアノテーション関連のサポートクラスが存在する。
		AnnotationSupport.findAnnotatedFields(testClass, MyEnv.class, predicate)
				.forEach(f -> {
					MyEnv myEnv = AnnotationSupport.findAnnotation(f, MyEnv.class).get();
					String value = myEnv.value();

					f.setAccessible(true);
					try {
						f.set(testInstance, System.getenv(value));
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				});

	}

	// ---------------------------------------------
	// parameter

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		return parameterContext.isAnnotated(MyEnv.class) && parameterContext.getParameter().getType() == String.class;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		MyEnv myEnv = parameterContext.findAnnotation(MyEnv.class).get();
		String value = myEnv.value();
		return System.getenv(value);
	}
}
