package epsilongtmyon.sandbox01;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

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

import epsilongtmyon.util.WorkbookSaver;

public class Sandbox01Main {

	public static void main(String[] args) throws IOException {
		start01(args);
	}

	private static void start01(String[] args) throws IOException {
		// ブック作成
		try (final Workbook workbook = WorkbookFactory.create(true)) {

			// シート追加して名前変更
			workbook.createSheet();
			workbook.setSheetName(0, "x1");

			// シート追加時に名前指定
			workbook.createSheet("x2");

			// アクティブなタブを選ぶ(??)
			workbook.setActiveSheet(1);
			workbook.setSelectedTab(1);

			WorkbookSaver.save(workbook);
		}
	}

	private static void start02(String[] args) throws IOException {
		// ブック作成
		try (final Workbook workbook = WorkbookFactory.create(true)) {
			final DataFormat dataFormat = workbook.createDataFormat();

			// 書式「文字列」
			final CellStyle textStyle = workbook.createCellStyle();
			textStyle.setDataFormat(dataFormat.getFormat("text"));

			final Sheet sheet1 = workbook.createSheet("s1");

			Row row1 = CellUtil.getRow(2, sheet1);
			Cell cell1 = CellUtil.getCell(row1, 5);
			cell1.setCellValue("あいうえお");

			Row row2 = CellUtil.getRow(2, sheet1);
			Cell cell2 = CellUtil.getCell(row2, 3);
			cell2.setCellStyle(textStyle);
			cell2.setCellValue("000002");
			
			
			

			//列の中での最大を取得
			int lastCell = StreamSupport.stream(sheet1.spliterator(), false)
					.mapToInt(x -> x.getLastCellNum())
					.max().getAsInt();
			// 書式設定のデフォルト
			// ここにセットしたものがセルにスタイルがないときのデフォルト値になる
			IntStream.range(0, lastCell).forEach(i -> sheet1.setDefaultColumnStyle(i, textStyle));
			
			WorkbookSaver.save(workbook);
		}
	}


	private static void start03(String[] args) throws IOException {
		// ブック作成
		try (final Workbook workbook = WorkbookFactory.create(true)) {
			
			final DataFormat dataFormat = workbook.createDataFormat();

			// 書式「文字列」
			final Font textStyleFont = workbook.createFont();
			textStyleFont.setFontName("ＭＳ ゴシック");
			final CellStyle textStyle = workbook.createCellStyle();
			textStyle.setFont(textStyleFont);
			textStyle.setDataFormat(dataFormat.getFormat("text"));

			final Sheet sheet1 = workbook.createSheet("s1");
			
			//--------------------------------------------------------------

			// セルの塗りつぶしはForegroundColoでする。
			// Backgroundは塗りつぶしに模様を設定するときの背景の色
			final CellStyle cellStyle1 = workbook.createCellStyle();
			cellStyle1.cloneStyleFrom(textStyle);
			cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cellStyle1.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			
			Row row1 = CellUtil.getRow(2, sheet1);
			Cell cell1 = CellUtil.getCell(row1, 5);
			cell1.setCellStyle(cellStyle1);
			cell1.setCellValue("ぬりつぶし");

			//--------------------------------------------------------------
			// 文字の色

			final CellStyle cellStyle2 = workbook.createCellStyle();
			cellStyle2.cloneStyleFrom(textStyle);
			
			final Font font = workbook.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setFontName("ＭＳ ゴシック");
			cellStyle2.setFont(font);
			
			Row row2 = CellUtil.getRow(2, sheet1);
			Cell cell2 = CellUtil.getCell(row2, 3);
			cell1.setCellStyle(cellStyle2);
			cell2.setCellValue("000002");
			
			int lastCell = StreamSupport.stream(sheet1.spliterator(), false)
					.mapToInt(x -> x.getLastCellNum())
					.max().getAsInt();
			IntStream.range(0, lastCell).forEach(i -> sheet1.setDefaultColumnStyle(i, textStyle));

			WorkbookSaver.save(workbook);
		}
	}
}
