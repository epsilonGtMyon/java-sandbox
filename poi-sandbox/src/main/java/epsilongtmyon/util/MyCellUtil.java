package epsilongtmyon.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.util.CellUtil;

/**
 * セルのユーティリティ
 * 
 * 使ってるCopyUtilは POI 5.2.0から使えるみたい
 */
public class MyCellUtil {
	
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

	private MyCellUtil() {
	}

	/**
	 * セルをコピーします。
	 * 
	 * @param srcCell コピー元セル
	 * @param destCell コピー先セル
	 */
	public static void copyCell(Cell srcCell, Cell destCell) {
		CellUtil.copyCell(srcCell, destCell, defaultPolicy, null);
	}

	/**
	 * セルのスタイルをコピーします。
	 * 
	 * @param srcCell コピー元セル
	 * @param destCell コピー先セル
	 */
	public static void copyCellStyle(Cell srcCell, Cell destCell) {
		CellUtil.copyCell(srcCell, destCell, copyStylePolicy, null);
	}
	
	/**
	 * セルの値をコピーします。
	 * 
	 * @param srcCell コピー元セル
	 * @param destCell コピー先セル
	 */
	public static void copyCellValue(Cell srcCell, Cell destCell) {
		CellUtil.copyCell(srcCell, destCell, copyValuePolicy, null);
	}
	
}
