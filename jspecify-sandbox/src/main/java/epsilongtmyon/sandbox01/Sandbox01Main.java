package epsilongtmyon.sandbox01;

import org.springframework.util.StringUtils;

import epsilongtmyon.sandbox01.util.StringUtil;

public class Sandbox01Main {

	public static void main(String[] args) {
	}

	private static void trim1() {

		String text = StringUtil.trim("abc");

		// trimの戻り値がNullableなのでここでエラー検出
		System.out.println(text.length());

	}

	private static void trim2() {
		String text = StringUtil.trim("def");
		if (text != null) {
			// nullを除外しているのでエラーにならない
			System.out.println(text.length());
		}
	}

	private static void xxx1() {

		// xxx自体は何もアノテーションつけてないが
		// package-infoでNullMarked つけてるのでnullを渡すとエラー
		StringUtil.xxx(null);
	}

	private static void capitalize() {

		// Springもorg.springframework.utilのpackage-info.javaでNullMarkedがついている。
		String text = StringUtils.capitalize(null);
		System.out.println(text.length());
	}

}
