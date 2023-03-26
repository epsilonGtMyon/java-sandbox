package epsilongtmyon.sandbox03;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;

import epsilongtmyon.util.MyRowUtil;
import epsilongtmyon.util.ResourceUtil;
import epsilongtmyon.util.WorkbookSaver;

public class Sandbox03 {

	public static void main(String[] args) throws Exception {
		start01(args);
	}

	private static void start01(String[] args) throws EncryptedDocumentException, IOException {

		try (final Workbook workbook = ResourceUtil.getResourceAsWorkbook("sandbox03/Sandbox03/template01.xlsx")) {

			//------------------------
			List<Sandbox03Record> records = new ArrayList<>();
			records.add(new Sandbox03Record("あれ", new BigDecimal("100"), new BigDecimal("1")));
			records.add(new Sandbox03Record("これ", new BigDecimal("200"), new BigDecimal("2")));
			records.add(new Sandbox03Record("それ", new BigDecimal("300"), new BigDecimal("3")));
			//------------------------

			final Sheet sheet1 = workbook.getSheetAt(0);

			int templateRowNum = 2;
			final Row templateRow = sheet1.getRow(templateRowNum);

			// レコード数分行追加(下にずらす)
			MyRowUtil.insertRows(sheet1, templateRowNum + 1, records.size());

			// ずらしたところにテンプレート行の内容をコピー
			for (int i = 1, n = records.size(); i <= n; i++) {
				final Row destRow = CellUtil.getRow(templateRowNum + i, sheet1);
				MyRowUtil.copyRow(templateRow, destRow);
			}

			// テンプレートの行を削除して
			MyRowUtil.removeRow(sheet1, templateRowNum);
			//------------------------

			for (int i = 0, n = records.size(); i < n; i++) {
				Sandbox03Record record = records.get(i);
				final Row targetRow = CellUtil.getRow(templateRowNum + i, sheet1);

				Cell cell1 = CellUtil.getCell(targetRow, 1);
				cell1.setCellValue(record.productName());

				Cell cell2 = CellUtil.getCell(targetRow, 2);
				cell2.setCellValue(record.tanka().doubleValue());

				Cell cell3 = CellUtil.getCell(targetRow, 3);
				cell3.setCellValue(record.suryo().doubleValue());

				Cell cell4 = CellUtil.getCell(targetRow, 4);
				cell4.setCellValue(record.total().doubleValue());

			}

			WorkbookSaver.save(workbook);
		}
	}
}
