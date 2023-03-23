package epsilongtmyon.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ResourceUtil {

	public static InputStream getResourceAsStream(String name) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}

	public static Workbook getResourceAsWorkbook(String name) throws EncryptedDocumentException, IOException {
		try (InputStream is = getResourceAsStream(name)) {
			return WorkbookFactory.create(is);
		}
	}
}
