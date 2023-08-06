package epsilongtmyon.setting;

import java.util.Collections;
import java.util.Map;

public class EnvironmentSetting implements Setting {

	private final Map<String, String> env;

	public EnvironmentSetting() {
		this.env = Collections.unmodifiableMap(loadEnv());
	}

	private Map<String, String> loadEnv() {
		return System.getenv();
	}

	@Override
	public String getProperty(String key) {
		return env.get(key);
	}
}
