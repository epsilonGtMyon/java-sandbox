package epsilongtmyon.sandbox01.util;

import org.jspecify.annotations.Nullable;

public class StringUtil {

	public static boolean isEmpty(@Nullable String value) {
		return value == null || value.isEmpty();
	}

	public static boolean isNotEmpty(@Nullable String value) {
		return !isEmpty(value);
	}

	public static void xxx(String text) {
		//noop
	}

	// メソッドの戻り値がNull許容になる
	@Nullable
	public static String trim(@Nullable String value) {
		return value == null ? null : value.trim();
	}
}
