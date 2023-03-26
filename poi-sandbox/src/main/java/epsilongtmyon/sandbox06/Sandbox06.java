package epsilongtmyon.sandbox06;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import epsilongtmyon.util.MyColumnUtil;
import epsilongtmyon.util.MyRowUtil;
import epsilongtmyon.util.ResourceUtil;
import epsilongtmyon.util.WorkbookSaver;

public class Sandbox06 {

	public static void main(String[] args) throws Exception {
		start04(args);
	}

	// 列コピー
	private static void start01(String[] args) throws EncryptedDocumentException, IOException {
		try (final Workbook workbook = ResourceUtil.getResourceAsWorkbook("sandbox06/Sandbox06/template01.xlsx")) {
			final Sheet sheet1 = workbook.getSheetAt(0);

			MyColumnUtil.copyColumn(sheet1, 1, 5);
			MyColumnUtil.copyColumnStyle(sheet1, 2, 6);
			MyColumnUtil.copyColumnValue(sheet1, 3, 7);

			WorkbookSaver.save(workbook);
		}
	}

	// 列コピー
	private static void start02(String[] args) throws EncryptedDocumentException, IOException {
		try (final Workbook workbook = ResourceUtil.getResourceAsWorkbook("sandbox06/Sandbox06/template01.xlsx")) {
			final Sheet sheet1 = workbook.getSheetAt(0);

			sheet1.shiftColumns(1, 1, 1);

			MyColumnUtil.copyColumn(sheet1, 1, 2);

			// java.lang.IndexOutOfBoundsException がおきてしまう...
			WorkbookSaver.save(workbook);
		}
	}

	//行操作
	private static void start03(String[] args) throws EncryptedDocumentException, IOException {
		try (final Workbook workbook = ResourceUtil.getResourceAsWorkbook("sandbox06/Sandbox06/template01.xlsx")) {
			final Sheet sheet1 = workbook.getSheetAt(0);

			// 上にずれるので後にある行から消す
			MyRowUtil.removeRow(sheet1, 8);
			MyRowUtil.removeRow(sheet1, 7);

			WorkbookSaver.save(workbook);
		}
	}


	//行操作
	private static void start04(String[] args) throws EncryptedDocumentException, IOException {
		try (final Workbook workbook = ResourceUtil.getResourceAsWorkbook("sandbox06/Sandbox06/template01.xlsx")) {
			final Sheet sheet1 = workbook.getSheetAt(0);

			MyRowUtil.removeRows(sheet1, 7, 9);
			MyRowUtil.insertRows(sheet1, 2, 3);
			WorkbookSaver.save(workbook);
		}
	}
}
