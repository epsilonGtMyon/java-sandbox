package epsilongtmyon.util;

import java.util.function.BiConsumer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;

/**
 * 列のユーティリティ
 */
public class MyColumnUtil {

	private static void copyInternal(Sheet sheet, int srcColumnIndex, int destColumnIndex,
			BiConsumer<Cell, Cell> copyHandler) {

		sheet.forEach(row -> {
			final short lastCellNum = row.getLastCellNum();
			if (lastCellNum < srcColumnIndex) {
				return;
			}

			final Cell srcCell = row.getCell(srcColumnIndex);
			if (srcCell == null) {
				return;
			}

			final Cell destCell = CellUtil.getCell(row, destColumnIndex);

			copyHandler.accept(srcCell, destCell);
		});
	}
	
	/**
	 * 列をコピーします。
	 * 
	 * @param sheet 対象のシート
	 * @param srcColumnIndex コピー元の列インデックス(0～) 
	 * @param destColumnIndex コピー先の列インデックス(0～)
	 */
	public static void copyColumn(Sheet sheet, int srcColumnIndex, int destColumnIndex) {
		copyInternal(sheet, srcColumnIndex, destColumnIndex, (srcCell, destCell) -> {
			MyCellUtil.copyCell(srcCell, destCell);
		});
	}

	/**
	 * 列のスタイルをコピーします。
	 * 
	 * @param sheet 対象のシート
	 * @param srcColumnIndex コピー元の列インデックス(0～) 
	 * @param destColumnIndex コピー先の列インデックス(0～)
	 */
	public static void copyColumnStyle(Sheet sheet, int srcColumnIndex, int destColumnIndex) {
		copyInternal(sheet, srcColumnIndex, destColumnIndex, (srcCell, destCell) -> {
			MyCellUtil.copyCellStyle(srcCell, destCell);
		});
	}

	/**
	 * 列の値をコピーします。
	 * 
	 * @param sheet 対象のシート
	 * @param srcColumnIndex コピー元の列インデックス(0～) 
	 * @param destColumnIndex コピー先の列インデックス(0～)
	 */
	public static void copyColumnValue(Sheet sheet, int srcColumnIndex, int destColumnIndex) {
		copyInternal(sheet, srcColumnIndex, destColumnIndex, (srcCell, destCell) -> {
			MyCellUtil.copyCellValue(srcCell, destCell);
		});
	}
}
