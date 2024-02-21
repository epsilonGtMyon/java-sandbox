package epsilongtmyon.extension.parameter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyEnvironment {

	private Map<String, String> env;

	public MyEnvironment() {
		this.env = Collections.unmodifiableMap(new HashMap<>(System.getenv()));
	}

	public String getValue(String key) {
		return env.get(key);
	}

	public Map<String, String> getEnv() {
		return env;
	}

	@Override
	public String toString() {
		return "MyEnvironment [env=" + env + "]";
	}

}
