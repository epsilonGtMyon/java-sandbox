package epsilongtmyon.basic.conditional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

// EnabledOnXxxxで実行条件を指定できる。
// 環境変数とかシステムプロパティを使ったものもある。
// アノテーション化することで繰り返しの指定をなくすこともできる。

public class ConditionalTest {

	@Test
	@EnabledOnOs(OS.WINDOWS)
	void onWindows(TestInfo tInfo) {
		System.out.println(tInfo.getDisplayName());
	}

	@Test
	@DisabledOnOs(OS.WINDOWS)
	void onNotWindows(TestInfo tInfo) {
		System.out.println(tInfo.getDisplayName());
	}

	//----------------

	@Test
	@EnabledOnJre(JRE.JAVA_11)
	void onJava11(TestInfo tInfo) {
		System.out.println(tInfo.getDisplayName());
	}

	@Test
	@DisabledOnJre(JRE.JAVA_11)
	void onNotJava11(TestInfo tInfo) {
		System.out.println(tInfo.getDisplayName());
	}

	//---------
	// カスタム条件
	// メソッド名を"example.ExternalCondition#customCondition"と書くことで外部のクラスのメソッドも指定できる。
	//
	// カスタムメソッドをstaticにする必要があるのは?
	// EnabledIfなどがクラスレベルのとき
	// ParameterizedTest ,TestTemplate のとき
	// 条件のメソッドが外部のクラスのとき

	@Test
	@EnabledIf("custom")
	void customTestEnabled(TestInfo tInfo) {
		System.out.println(tInfo.getDisplayName());
	}

	@Test
	@DisabledIf("custom")
	void customTestDisabled(TestInfo tInfo) {
		System.out.println(tInfo.getDisplayName());
	}

	boolean custom() {
		return true;
	}

}
