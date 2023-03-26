package epsilongtmyon.sandbox06;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import epsilongtmyon.util.MyColumnUtil;
import epsilongtmyon.util.ResourceUtil;
import epsilongtmyon.util.WorkbookSaver;

public class Sandbox06 {

	public static void main(String[] args) throws Exception {
		start01(args);
	}

	private static void start01(String[] args) throws EncryptedDocumentException, IOException {
		try (final Workbook workbook = ResourceUtil.getResourceAsWorkbook("sandbox06/Sandbox06/template01.xlsx")) {
			final Sheet sheet1 = workbook.getSheetAt(0);

			MyColumnUtil.copyColumn(sheet1, 1, 5);
			MyColumnUtil.copyColumnStyle(sheet1, 2, 6);
			MyColumnUtil.copyColumnValue(sheet1, 3, 7);

			WorkbookSaver.save(workbook);
		}
	}

}
