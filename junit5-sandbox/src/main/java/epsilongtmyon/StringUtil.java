package epsilongtmyon;

public class StringUtil {

	public static boolean isEmpty(String value) {
		return value == null || value.isEmpty();
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static boolean isBlank(String value) {
		return value == null || value.isBlank();
	}

	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}
}
