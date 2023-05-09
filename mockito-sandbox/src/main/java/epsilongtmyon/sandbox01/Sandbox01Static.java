package epsilongtmyon.sandbox01;

public class Sandbox01Static {

	public static String repeat2(String text) {
		return text == null ? null : text.repeat(2);
	}

	public static String repeat3(String text) {
		return text == null ? null : text.repeat(3);
	}

	public static String repeat4(String text) {
		return text == null ? null : text.repeat(4);
	}

	//---------------------

	public static String publicHello(String text) {
		return protectedHello(text);
	}

	protected static String protectedHello(String text) {
		return String.format("hello %s", text);
	}
}
