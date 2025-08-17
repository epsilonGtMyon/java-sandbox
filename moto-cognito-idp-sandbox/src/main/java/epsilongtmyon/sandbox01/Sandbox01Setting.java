package epsilongtmyon.sandbox01;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sandbox01Setting {

	private final String userPoolId;

	private final String clientId;

	private final String clientSecret;

	public Sandbox01Setting(String userPoolId, String clientId, String clientSecret) {
		super();
		this.userPoolId = userPoolId;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public static void main(String[] args) {
		load();
	}

	/**
	 * プロジェクト直下の.envファイルから
	 * @return
	 */
	public static Sandbox01Setting load() {
		return load(".env");
	}

	public static Sandbox01Setting load(String file) {
		Map<String, String> map = loadAsMap(file);

		return new Sandbox01Setting(
				map.get("USER_POOL_ID"),
				map.get("CLIENT_ID"),
				map.get("CLIENT_SECRET"));
	}

	public static Map<String, String> loadAsMap(String file) {
		Path p = Path.of(file).toAbsolutePath();
		Map<String, String> map = new HashMap<>();

		if (Files.notExists(p)) {
			return map;
		}

		try {
			List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);
			for (String line : lines) {
				int index = line.indexOf("=");
				if (index < 0) {
					continue;
				}
				String key = line.substring(0, index).trim();
				String value = line.substring(index + 1).trim();

				map.put(key, value);
			}
			return map;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}

	public String getUserPoolId() {
		return userPoolId;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

}
