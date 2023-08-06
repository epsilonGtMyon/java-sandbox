package epsilongtmyon.setting;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SystemPropertiesSetting implements Setting {

	private final Map<String, String> systemProperties;

	public SystemPropertiesSetting() {
		this.systemProperties = Collections.unmodifiableMap(loadSystemProperties());
	}

	private Map<String, String> loadSystemProperties() {
		return System.getProperties()

				.entrySet()

				.stream()

				.collect(Collectors.toMap(e -> Objects.toString(e.getKey()), e -> Objects.toString(e.getValue())));
	}

	@Override
	public String getProperty(String key) {
		return systemProperties.get(key);
	}

}
