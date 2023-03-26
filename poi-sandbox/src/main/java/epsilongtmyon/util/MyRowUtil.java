package epsilongtmyon.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * 行に関するユーティリティ
 */
public class MyRowUtil {

	/** デフォルトのコピーポリシー */
	private static final CellCopyPolicy defaultPolicy = new CellCopyPolicy();

	/** 何もしないコピーポリシー */
	private static final CellCopyPolicy allFalsePolicy = new CellCopyPolicy.Builder()
			.cellValue(false)
			.cellStyle(false)
			.cellFormula(false)
			.copyHyperlink(false)
			.mergeHyperlink(false)

			.rowHeight(false)
			.condenseRows(false)

			.mergedRegions(false)
			.build();

	/** スタイルだけのコピーポリシー */
	private static final CellCopyPolicy copyStylePolicy = allFalsePolicy.createBuilder()
			.cellStyle(true)
			.build();

	/** 値だけのコピーポリシー */
	private static final CellCopyPolicy copyValuePolicy = allFalsePolicy.createBuilder()
			.cellValue(true)
			.build();

	private MyRowUtil() {
	}

	/**
	 * 行をコピーします。
	 * 
	 * @param srcRow コピー元
	 * @param destRow コピー先
	 */
	public static void copyRow(Row srcRow, Row destRow) {
		copyRow(srcRow, destRow, defaultPolicy);
	}

	/**
	 * 行のスタイルをコピーします。
	 * 
	 * @param srcRow コピー元
	 * @param destRow コピー先
	 */
	public static void copyRowStyle(Row srcRow, Row destRow) {
		copyRow(srcRow, destRow, copyStylePolicy);
	}

	/**
	 * 行の値をコピーします。
	 * 
	 * @param srcRow コピー元
	 * @param destRow コピー先
	 */
	public static void copyRowValue(Row srcRow, Row destRow) {
		copyRow(srcRow, destRow, copyValuePolicy);
	}

	private static void copyRow(Row srcRow, Row destRow, CellCopyPolicy cellCopyPolicy) {
		if (destRow instanceof XSSFRow destXssfRow) {
			destXssfRow.copyRowFrom(srcRow, cellCopyPolicy);
		} else if (destRow instanceof HSSFRow destHssfRow) {
			destHssfRow.copyRowFrom(srcRow, cellCopyPolicy);
		} else {
			throw new IllegalArgumentException("unknown destRow class " + destRow.getClass().getName());
		}
	}
	
	

	/**
	 * 空行を挿入します。
	 * 
	 * @param sheet 対象のシート
	 * @param targetRownum 対象の行番号
	 * @param rowCount 行数
	 */
	public static void insertRows(Sheet sheet, int targetRownum, int rowCount) {
		final int lastRowNum = sheet.getLastRowNum();
		if (targetRownum > lastRowNum) {
			// 最終行よりも後なので何もする必要ないはず
			return;
		}
		sheet.shiftRows(targetRownum, lastRowNum, rowCount, true, true);
	}
}
