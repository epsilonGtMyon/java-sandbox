package epsilongtmyon.sandbox02;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;

import epsilongtmyon.util.WorkbookSaver;

public class Sandbox02Main {

	public static void main(String[] args) throws IOException {
		start01(args);
	}

	private static void start01(String[] args) throws IOException {
		// ブック作成
		try (final Workbook workbook = WorkbookFactory.create(true)) {

			final DataFormat dataFormat = workbook.createDataFormat();

			// 書式「文字列」
			final CellStyle textStyle = workbook.createCellStyle();
			textStyle.setDataFormat(dataFormat.getFormat("text"));
			// フォント
			final Font textStyleFont = workbook.createFont();
			textStyleFont.setFontName("ＭＳ ゴシック");
			textStyle.setFont(textStyleFont);

			//---------------------------------------------

			final Sheet sheet1 = workbook.createSheet("s1");

			//--------------------------------------------------------------
			List<Sandbox02Record> records = IntStream.range(1, 5)
					.mapToObj(i -> new Sandbox02Record("aaa-" + i, "bbb-" + i, "ccc-" + i)).toList();
			
			final CellStyle gridStyle = workbook.createCellStyle();
			gridStyle.setBorderTop(BorderStyle.THIN);
			gridStyle.setBorderBottom(BorderStyle.THIN);
			gridStyle.setBorderLeft(BorderStyle.THIN);
			gridStyle.setBorderRight(BorderStyle.THIN);
			final Font gridStyleFont = workbook.createFont();
			gridStyleFont.setFontName("ＭＳ ゴシック");
			gridStyle.setFont(gridStyleFont);
			for(int i = 0,n = records.size(); i< n; i++) {
				final Sandbox02Record rec = records.get(i);
				Row row1 = CellUtil.getRow(i, sheet1);
				
				Cell cell0 = CellUtil.getCell(row1, 0);
				cell0.setCellStyle(gridStyle);
				cell0.setCellValue(rec.value01());

				Cell cell1 = CellUtil.getCell(row1, 1);
				cell1.setCellStyle(gridStyle);
				cell1.setCellValue(rec.value02());

				Cell cell2 = CellUtil.getCell(row1, 2);
				cell2.setCellStyle(gridStyle);
				cell2.setCellValue(rec.value03());
			}

			//---------------------------------------------
			int lastCell = StreamSupport.stream(sheet1.spliterator(), false)
					.mapToInt(x -> x.getLastCellNum())
					.max().getAsInt();
			IntStream.range(0, lastCell).forEach(i -> sheet1.setDefaultColumnStyle(i, textStyle));

			WorkbookSaver.save(workbook);
		}
	}
}
