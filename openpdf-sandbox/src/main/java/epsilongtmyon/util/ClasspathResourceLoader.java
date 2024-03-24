package epsilongtmyon.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public class ClasspathResourceLoader {

	public byte[] readAllBytes(String resourceName) {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try (InputStream is = classLoader.getResourceAsStream(resourceName);
				BufferedInputStream bis = new BufferedInputStream(is);) {

			return bis.readAllBytes();

		} catch (IOException ioEx) {
			throw new UncheckedIOException(ioEx);
		}
	}
}
