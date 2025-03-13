package epsilongtmyon.support.dbunit;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;

public class DbAssertion {

	/**
	 * データセットを検証します。
	 * 
	 * テーブルの列は期待値側のテーブルの列のみを検証します。
	 * 
	 * @param expectedDataSet 期待値側のデータセット
	 * @param actualDataSet 実際のデータセット
	 * @throws DatabaseUnitException
	 */
	public static void assertEquals(IDataSet expectedDataSet, IDataSet actualDataSet) throws DatabaseUnitException {

		final String[] tableNames = expectedDataSet.getTableNames();
		for (String tableName : tableNames) {

			ITable expectedTable = expectedDataSet.getTable(tableName);
			ITable actualTable = actualDataSet.getTable(tableName);

			assertEquals(expectedTable, actualTable);
		}

	}

	/**
	 * テーブルを検証します。
	 * 
	 * テーブルの列は期待値側のテーブルの列のみを検証します。
	 * 
	 * @param expectedTable 期待値側のテーブル
	 * @param actualTable 実際のテーブル
	 * @throws DatabaseUnitException
	 */
	public static void assertEquals(ITable expectedTable, ITable actualTable) throws DatabaseUnitException {

		Column[] expectedColumns = expectedTable.getTableMetaData().getColumns();
		ITable filteredActualTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedColumns);

		Assertion.assertEquals(expectedTable, filteredActualTable);
	}
}
