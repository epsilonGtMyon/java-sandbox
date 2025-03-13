package epsilongtmyon.support.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ResourceUtil {

	public static InputStream getResourceAsStream(String name) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}

	public static Reader getResourceAsReader(String name) {
		return getResourceAsReader(name, StandardCharsets.UTF_8);
	}

	public static Reader getResourceAsReader(String name, Charset charset) {
		InputStream in = getResourceAsStream(name);
		if (in == null) {
			return null;
		}
		return new InputStreamReader(in, charset);
	}

	public static String getResourceAsString(String name) {
		try (Reader r = getResourceAsReader(name);
				BufferedReader br = new BufferedReader(r)) {

			StringBuilder b = new StringBuilder();
			int i;
			while ((i = br.read()) > 0) {
				b.append((char) i);
			}
			return b.toString();

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}

	public static Properties getResourceAsProperties(String name) {
		try (Reader r = getResourceAsReader(name)) {
			if (r == null) {
				return null;
			}
			Properties props = new Properties();
			props.load(r);
			return props;

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
