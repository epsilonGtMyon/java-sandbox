package epsilongtmyon.sandbox.image;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import epsilongtmyon.util.ClasspathResourceLoader;
import epsilongtmyon.util.WorkPath;

/**
 * 画像を出力
 */
public class ImageSandbox {


	private static Path root = WorkPath.get(ImageSandbox.class);

	public static void main(String[] args) throws IOException {

		Files.createDirectories(root);
		execute();
	}
	

	private static void execute() throws IOException {
		Path path = root.resolve("Image.pdf");

		ClasspathResourceLoader loader = new ClasspathResourceLoader();

		try (OutputStream fos = Files.newOutputStream(path);
				Document document = new Document();) {
			PdfWriter.getInstance(document, fos);
			document.open();

			document.add(new Paragraph("Image!!"));

			Image pngImage = Image.getInstance(loader.readAllBytes("image/image.png"));
			//pngImage.setRotation((float)(Math.PI / 4));
			document.add(pngImage);
		}

	}

}
