package epsilongtmyon.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.poi.ss.usermodel.Workbook;

public class WorkbookSaver {

	public static void save(Workbook workbook) {

		try {
			Path bookPath = Path.of(".wookbook", System.currentTimeMillis() + ".xlsx");
			createParentDirectories(bookPath);
			try (OutputStream out = Files.newOutputStream(bookPath)) {
				workbook.write(out);
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}

	private static void createParentDirectories(Path path) throws IOException {
		Path parent = path.getParent();
		if (Files.notExists(parent)) {
			Files.createDirectories(parent);
		}
	}
}
