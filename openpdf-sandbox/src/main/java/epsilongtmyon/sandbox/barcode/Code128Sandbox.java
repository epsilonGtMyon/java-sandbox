package epsilongtmyon.sandbox.barcode;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfWriter;

import epsilongtmyon.util.WorkPath;

public class Code128Sandbox {

	private static Path root = WorkPath.get(Code128Sandbox.class);

	public static void main(String[] args) throws IOException {

		Files.createDirectories(root);

		execute();
	}

	private static void execute() throws IOException {
		Path path = root.resolve("code128.pdf");

		try (OutputStream fos = Files.newOutputStream(path);
				Document document = new Document();) {
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			document.open();

			Barcode128 b1 = new Barcode128();
			b1.setCodeType(Barcode.CODE128);
			b1.setCode("ABCDE123456");
			Image bImage1 = b1.createImageWithBarcode(writer.getDirectContent(), null, null);
			document.add(bImage1);
			
			document.newPage();

			Barcode128 b2 = new Barcode128();
			b2.setCodeType(Barcode.CODE128);
			b2.setCode("zxyw9100_");
			Image bImage2 = b2.createImageWithBarcode(writer.getDirectContent(), null, null);
			document.add(bImage2);
		}
	}
}
