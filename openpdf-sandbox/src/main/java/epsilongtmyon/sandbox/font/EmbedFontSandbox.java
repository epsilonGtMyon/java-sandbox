package epsilongtmyon.sandbox.font;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import epsilongtmyon.util.WorkPath;

public class EmbedFontSandbox {

	private static Path root = WorkPath.get(EmbedFontSandbox.class);

	public static void main(String[] args) throws IOException {

		Files.createDirectories(root);

		executeJapaneseFont();
		
		executeIPAFont();
	}
	
	private static void executeJapaneseFont() throws IOException {
		Path path = root.resolve("jpn-font.pdf");

		BaseFont bf = BaseFont.createFont("HeiseiKakuGo-W5", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font f = new Font(bf, 12f);
		
		try (OutputStream fos = Files.newOutputStream(path);
				Document document = new Document();) {
			PdfWriter.getInstance(document, fos);
			document.open();


			Paragraph p1 = new Paragraph();
			p1.setFont(f);
			p1.add("12344");
			p1.add("\r\n");
			p1.add("abcdeABCDE");
			p1.add("\r\n");
			p1.add("あいうえお");
			document.add(p1);
			
			// デフォルトだと日本語でない
			Paragraph p2 = new Paragraph();
			p2.add("12344");
			p2.add("\r\n");
			p2.add("abcdeABCDE");
			p2.add("\r\n");
			p2.add("あいうえお");
			document.add(p2);
			
		}
	}

	private static void executeIPAFont() throws IOException {
		Path path = root.resolve("ipa-font.pdf");

		BaseFont bfGodhic = BaseFont.createFont("font/ipaexg.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		BaseFont bfMincho = BaseFont.createFont("font/ipaexm.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		Font fGodhic = new Font(bfGodhic, 12f);
		Font fMincho = new Font(bfMincho, 12f);

		try (OutputStream fos = Files.newOutputStream(path);
				Document document = new Document();) {
			PdfWriter.getInstance(document, fos);
			document.open();

			Paragraph p1 = new Paragraph();
			p1.setFont(fGodhic);
			p1.add("12344");
			p1.add("\r\n");
			p1.add("abcdeABCDE");
			p1.add("\r\n");
			p1.add("あいうえお");
			document.add(p1);

			Paragraph p2 = new Paragraph();
			p2.setFont(fMincho);
			p2.add("12344");
			p2.add("\r\n");
			p2.add("abcdeABCDE");
			p2.add("\r\n");
			p2.add("あいうえお");
			document.add(p2);
		}
	}
		
}
