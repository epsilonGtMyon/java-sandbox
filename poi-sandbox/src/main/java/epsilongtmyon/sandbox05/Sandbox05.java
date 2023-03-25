package epsilongtmyon.sandbox05;

import java.io.IOException;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;

import epsilongtmyon.util.CellStyleMapBuilder;
import epsilongtmyon.util.WorkbookSaver;

public class Sandbox05 {

	public static void main(String[] args) throws IOException {
		start01(args);
	}

	private static void start01(String[] args) throws IOException {
		
		// ブックには持てるスタイルの数に制限があり、セル1つに対して新しいCellStyleを使っていては限界がくるし効率が悪い
		// CellUtil#setCellStyleProperties を使うとブックの中のセルスタイルと突き合わせて、同じものがあれば再利用するようになっている
		// ただMapを作るのが少し面倒そうなのでbuilderを用意してやってみるようにした
		
		// コンソールを見るとブック内のセルスタイルの数が頭打ちしているのが確認できる。

		try (Workbook workbook = WorkbookFactory.create(true)) {
			final DataFormat dataFormat = workbook.createDataFormat();

			// 書式「文字列」
			final CellStyle textStyle = workbook.createCellStyle();
			textStyle.setDataFormat(dataFormat.getFormat("text"));
			// フォント
			final Font textStyleFont = workbook.createFont();
			textStyleFont.setFontName("ＭＳ ゴシック");
			textStyle.setFont(textStyleFont);

			//-------------------------------

			final Sheet sheet1 = workbook.createSheet("シート");

			for (int i = 0; i < 20; i++) {
				final Row row = CellUtil.getRow(i, sheet1);
				
				System.out.println(workbook.getNumCellStyles());
				final Cell cell0 = CellUtil.getCell(row, 0);
				CellStyleMapBuilder.builder()
						.borderBottom(BorderStyle.THIN)
						.borderTop(BorderStyle.THIN)
						.borderLeft(BorderStyle.THIN)
						.borderRight(BorderStyle.THIN)
						.setCellStyleToCell(cell0);
				cell0.setCellValue("値１-" + i);
				
				System.out.println(workbook.getNumCellStyles());
				final Cell cell1 = CellUtil.getCell(row, 1);
				CellStyleMapBuilder.builder()
						.fillPattern(FillPatternType.SOLID_FOREGROUND)
						.fillForegroundColor(IndexedColors.AQUA.getIndex())
						.setCellStyleToCell(cell1);
				cell1.setCellValue("値２-" + i);

				System.out.println(workbook.getNumCellStyles());
				final Cell cell2 = CellUtil.getCell(row, 2);
				CellStyleMapBuilder.builder()
						.fillPattern(FillPatternType.SOLID_FOREGROUND)
						.fillForegroundColor(IndexedColors.DARK_RED.getIndex())
						.setCellStyleToCell(cell2);
				cell2.setCellValue("値３-" + i);

				System.out.println(workbook.getNumCellStyles());
				final Cell cell3 = CellUtil.getCell(row, 3);
				CellStyleMapBuilder.builder()
						.dataFormat(textStyle.getIndex())
						.setCellStyleToCell(cell3);
				cell3.setCellValue("値４-" + i);
			}

			WorkbookSaver.save(workbook);
		}
	}
}
