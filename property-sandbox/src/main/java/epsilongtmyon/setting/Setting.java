package epsilongtmyon.setting;

import java.util.Optional;

public interface Setting {

	String getProperty(String key);

	default Optional<String> getOptionalProperty(String key) {
		return Optional.ofNullable(getProperty(key));
	}
}
