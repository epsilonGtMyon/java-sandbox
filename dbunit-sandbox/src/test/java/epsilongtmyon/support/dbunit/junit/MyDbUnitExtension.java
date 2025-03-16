package epsilongtmyon.support.dbunit.junit;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.support.AnnotationSupport;

import epsilongtmyon.support.dbunit.DataSetProvider;
import epsilongtmyon.support.dbunit.DbAssertion;
import epsilongtmyon.support.dbunit.junit.annotation.AssertDataSet;
import epsilongtmyon.support.dbunit.junit.annotation.SetUpDataSet;
import epsilongtmyon.support.util.StringUtil;

/**
 * DBUnitと統合する拡張
 */
public class MyDbUnitExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

	// --------------------------
	// テストメソッドの引数関係

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		Class<?> clazz = parameterContext.getParameter().getType();
		return clazz == MyDbUnitSupport.class || clazz.isAssignableFrom(IDatabaseTester.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context)
			throws ParameterResolutionException {
		Class<?> clazz = parameterContext.getParameter().getType();

		Store methodStore = getStore(context);
		MyDbUnitSupport dbSupport = MyDbUnitSupport.fromStore(methodStore);

		if (clazz == MyDbUnitSupport.class) {
			return dbSupport;
		}

		return dbSupport.getDbTester();
	}

	// --------------------------
	// テストの前後の処理

	// テスト実行前の処理
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		Method testMethod = context.getTestMethod().get();
		SetUpDataSet setupDataSet = AnnotationSupport.findAnnotation(testMethod, SetUpDataSet.class).orElse(null);
		if (setupDataSet == null) {
			return;
		}

		// テストメソッドにSetUpDataSetアノテーションがあればデータ投入

		Store methodStore = getStore(context);

		MyDbUnitSupport dbSupport = MyDbUnitSupport.fromStore(methodStore);
		IDatabaseTester dbTester = dbSupport.getDbTester();

		DataSetSource setupSource = DataSetSource.fromSetUpDataSet(setupDataSet);
		IDataSet setUpDataSet = loadDataSet(setupSource, context);
		dbTester.setDataSet(setUpDataSet);
		dbTester.onSetup();
	}

	// テスト実行後の処理
	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		Store methodStore = getStore(context);
		try {
			Optional<Throwable> executionException = context.getExecutionException();
			if (executionException.isPresent()) {
				// 例外起きてたら何もしない。
				return;
			}

			Method testMethod = context.getTestMethod().get();
			AssertDataSet assertDataSet = AnnotationSupport.findAnnotation(testMethod, AssertDataSet.class)
					.orElse(null);
			if (assertDataSet == null) {
				// 検証対象外
				return;
			}

			// テストメソッドにAssertDataSetアノテーションがあればデータ検証

			MyDbUnitSupport dbSupport = MyDbUnitSupport.fromStore(methodStore);
			IDatabaseConnection dbCon = dbSupport.getDbConnection();

			DataSetSource assertSource = DataSetSource.fromAssertDataSet(assertDataSet);

			// -------
			// 検証
			
			// 期待値
			IDataSet expectedDataSet = loadDataSet(assertSource, context);
			// 実データ
			IDataSet actualDataSet = dbCon.createDataSet();
			DbAssertion.assertEquals(expectedDataSet, actualDataSet);
		} finally {
			// DBUnit関係のリソースの解放
			MyDbUnitSupport.removeStore(methodStore);
		}

	}

	/**
	 * メソッドスコープのストアの取得
	 * 
	 * @param context
	 * @return
	 */
	private Store getStore(ExtensionContext context) {
		return context.getStore(Namespace.create(getClass(), context.getRequiredTestMethod()));
	}

	/**
	 * データセットを読み込む
	 * 
	 * @param source
	 * @param context
	 * @return
	 * @throws DataSetException
	 * @throws IOException
	 */
	private IDataSet loadDataSet(DataSetSource source, ExtensionContext context)
			throws DataSetException, IOException {
		String annFullPath = source.getFullPath();
		if (StringUtil.isNotEmpty(annFullPath)) {
			return DataSetProvider.getNoPrefix().loadXls(annFullPath);
		}
		Class<?> testClass = context.getRequiredTestClass();
		String resourcePrefix = testClass.getName().replaceAll("\\$", "_").replaceAll("\\.", "/");

		String annResourceName = source.getResourceName();

		final String resourceName = StringUtil.isNotEmpty(annResourceName)
				? annResourceName
				: context.getTestMethod().map(Method::getName).get() + source.getResourceSuffix();

		IDataSet dataSet = DataSetProvider.get(resourcePrefix).loadXls(resourceName);
		return dataSet;

	}

	public static class DataSetSource {

		private String fullPath;

		private String resourceName;

		private String resourceSuffix;

		public static DataSetSource fromSetUpDataSet(SetUpDataSet setUpDataSet) {
			var d = new DataSetSource();
			d.fullPath = setUpDataSet.fullPath();
			d.resourceName = setUpDataSet.resourceName();
			d.resourceSuffix = "_setup.xlsx";
			return d;
		}

		public static DataSetSource fromAssertDataSet(AssertDataSet assertDataSet) {
			var d = new DataSetSource();
			d.fullPath = assertDataSet.fullPath();
			d.resourceName = assertDataSet.resourceName();
			d.resourceSuffix = "_expected.xlsx";
			return d;
		}

		public String getFullPath() {
			return fullPath;
		}

		public String getResourceName() {
			return resourceName;
		}

		public String getResourceSuffix() {
			return resourceSuffix;
		}

	}

}
