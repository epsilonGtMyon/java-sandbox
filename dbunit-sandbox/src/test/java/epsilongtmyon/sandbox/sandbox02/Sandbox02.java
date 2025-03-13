package epsilongtmyon.sandbox.sandbox02;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.junit.jupiter.api.Test;

import epsilongtmyon.service.sample.SampleService;
import epsilongtmyon.support.db.DatabasaeInitializer;
import epsilongtmyon.support.dbunit.DataSetProvider;
import epsilongtmyon.support.dbunit.DatabaseTesterProvider;
import epsilongtmyon.support.dbunit.DbAssertion;

public class Sandbox02 {

	@Test
	public void start01() throws Exception {

		IDatabaseTester dbTester = DatabaseTesterProvider.get().getDatabaseTester();
		DataSetProvider dataSetProvider = DataSetProvider.get(Sandbox02.class);

		try {
			IDatabaseConnection dbCon = dbTester.getConnection();

			new DatabasaeInitializer().initializer(dbCon.getConnection());
			
			try {
				// データセットをCSVで用意
				// CSVは文字セットを明示せず読み込まれるためJava18以前のWindows環境では必要
				// Eclipseから実行した場合はUTF-8
				dbTester.setDataSet(dataSetProvider.loadCsv("test_data"));
				// セットアップの実行
				dbTester.onSetup();

				// ここでテスト対象の処理を実行する
				new SampleService().execute();

				// -------
				// 検証
				// 期待値をCSVで用意
				IDataSet expectedDataSet = dataSetProvider.loadCsv("test_expected");
				// 実データのデータセットはIDatabaseConnectionから
				IDataSet actualDataSet = dbCon.createDataSet();
				DbAssertion.assertEquals(expectedDataSet, actualDataSet);

			} finally {
				dbCon.close();
			}
		} finally {
			dbTester.onTearDown();
		}

	}

}
