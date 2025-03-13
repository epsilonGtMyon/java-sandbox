package epsilongtmyon.sandbox.sandbox01;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.junit.jupiter.api.Test;

import epsilongtmyon.service.sample.SampleService;
import epsilongtmyon.support.db.DatabasaeInitializer;
import epsilongtmyon.support.dbunit.DataSetProvider;
import epsilongtmyon.support.dbunit.DatabaseTesterProvider;
import epsilongtmyon.support.dbunit.DbAssertion;

public class Sandbox01 {

	@Test
	public void start01() throws Exception {

		IDatabaseTester dbTester = DatabaseTesterProvider.get().getDatabaseTester();
		DataSetProvider dataSetProvider = DataSetProvider.get(Sandbox01.class);

		try {
			IDatabaseConnection dbCon = dbTester.getConnection();

			new DatabasaeInitializer().initializer(dbCon.getConnection());

			try {
				// データセットをExcelで用意
				dbTester.setDataSet(dataSetProvider.loadXls("test_data.xlsx"));
				// セットアップの実行
				dbTester.onSetup();

				// ここでテスト対象の処理を実行する
				new SampleService().execute();

				// -------
				// 検証
				// 期待値をExcelで用意
				IDataSet expectedDataSet = dataSetProvider.loadXls("test_expected.xlsx");
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
