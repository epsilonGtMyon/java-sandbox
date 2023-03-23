package epsilongtmyon.sandbox04;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;

import epsilongtmyon.util.MyCellUtil;
import epsilongtmyon.util.MyRowUtil;
import epsilongtmyon.util.ResourceUtil;
import epsilongtmyon.util.WorkbookSaver;

public class Sandbox04 {

	public static void main(String[] args) throws Exception {
		start02(args);
	}

	private static void start01(String[] args) throws EncryptedDocumentException, IOException {
		try (final Workbook workbook = ResourceUtil.getResourceAsWorkbook("sandbox04/Sandbox04/template01.xlsx")) {
			final Sheet sheet1 = workbook.getSheetAt(0);

			final int lastRowNum = sheet1.getLastRowNum();

			for (int i = 1; i <= lastRowNum; i++) {
				final Row row = CellUtil.getRow(i, sheet1);
				// コピー元のセル
				final Cell srcCell = row.getCell(2);

				// 値のコピー
				final Cell destValueCell = CellUtil.getCell(row, 3);
				MyCellUtil.copyCellValue(srcCell, destValueCell);

				// スタイルのコピー
				final Cell destStyleCell = CellUtil.getCell(row, 4);
				MyCellUtil.copyCellStyle(srcCell, destStyleCell);

				// まとめてコピー
				final Cell destCell = CellUtil.getCell(row, 5);
				MyCellUtil.copyCell(srcCell, destCell);
			}

			WorkbookSaver.save(workbook);
		}
	}

	private static void start02(String[] args) throws EncryptedDocumentException, IOException {
		try (final Workbook workbook = ResourceUtil.getResourceAsWorkbook("sandbox04/Sandbox04/template02.xlsx")) {
			final Sheet sheet1 = workbook.getSheetAt(0);

			final Row srcRow1 = sheet1.getRow(3);

			// 値のコピー
			final Row destValueRow = CellUtil.getRow(4, sheet1);
			MyRowUtil.copyRowValue(srcRow1, destValueRow);

			// スタイルのコピー
			final Row destStyleRow = CellUtil.getRow(5, sheet1);
			MyRowUtil.copyRowStyle(srcRow1, destStyleRow);
			
			// まとめてコピー
			final Row destRow = CellUtil.getRow(6, sheet1);
			MyRowUtil.copyRow(srcRow1, destRow);

			WorkbookSaver.save(workbook);
		}
	}
}
