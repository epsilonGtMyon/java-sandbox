package epsilongtmyon.setting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesFileSetting implements Setting {

	private final Map<String, String> properties;

	public PropertiesFileSetting(String name) {
		this.properties = Collections.unmodifiableMap(loadProperties(name));
	}

	private Map<String, String> loadProperties(String name) {
		Properties p = new Properties();
		try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
				InputStreamReader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {

			p.load(r);

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		return p

				.entrySet()

				.stream()

				.collect(Collectors.toMap(e -> Objects.toString(e.getKey()), e -> Objects.toString(e.getValue())));
	}

	@Override
	public String getProperty(String key) {
		return properties.get(key);
	}
}
