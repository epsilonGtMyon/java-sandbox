package epsilongtmyon.basic.repeated;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

// RepeatedTestアノテーションで繰り返し実行のテストができる
// RepetitionInfoをパラメータに渡すことで繰り返しテストの情報を受け取ることができる。
// テストの名前を変更するときはname属性を使う
public class Repeated01Test {

	
	@RepeatedTest(5)
	void repeatTest01(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		System.out.println("-----");
		System.out.println(testInfo.getDisplayName());
		System.out.println(repetitionInfo.getCurrentRepetition());
		System.out.println(repetitionInfo.getTotalRepetitions());
		System.out.println(repetitionInfo.getFailureCount());
		System.out.println(repetitionInfo.getFailureThreshold());
	}
	

	// failureThresholdをつけると この失敗回数を超えると以降の繰り返し実行テストは行わない
	// ※「failureThresholdの回数以内だと成功とみなす」というわけではないので注意、余計な実行を減らすためのもの
	@RepeatedTest(value = 5, failureThreshold = 2)
	void repeatTest02(TestInfo testInfo, RepetitionInfo repetitionInfo) {
		System.out.println("-----");
		System.out.println(testInfo.getDisplayName());
		System.out.println(repetitionInfo.getFailureCount());
		System.out.println(repetitionInfo.getFailureThreshold());
		

        if (repetitionInfo.getCurrentRepetition() < 4) {
            fail("Boom!");
        }
	}
}
