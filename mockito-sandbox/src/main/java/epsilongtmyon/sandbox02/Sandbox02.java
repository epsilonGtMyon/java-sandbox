package epsilongtmyon.sandbox02;

public class Sandbox02 {

	public String repeat2(String text) {
		return text == null ? null : text.repeat(2);
	}

	public String repeat3(String text) {
		return text == null ? null : text.repeat(3);
	}

	public String repeat4(String text) {
		return text == null ? null : text.repeat(4);
	}

	//---------------------

	public String publicHello(String text) {
		return protectedHello(text);
	}

	protected String protectedHello(String text) {
		return String.format("hello %s", text);
	}

	//---------------------

	public String publicHey(String text) {
		return privateHey(text);
	}

	private String privateHey(String text) {
		return String.format("hey %s", text);
	}
}
