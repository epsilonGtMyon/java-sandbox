package epsilongtmyon.sandbox.sandbox03;

import org.dbunit.IDatabaseTester;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import epsilongtmyon.service.sample.SampleService;
import epsilongtmyon.support.dbunit.junit.MyDbUnitExtension;
import epsilongtmyon.support.dbunit.junit.MyDbUnitSupport;
import epsilongtmyon.support.dbunit.junit.annotation.AssertDataSet;
import epsilongtmyon.support.dbunit.junit.annotation.SetUpDataSet;

// JUnit5 のExtension使って、事前DB投入と事後DB検証、メソッド引数の解決を行う

@ExtendWith({ MyDbUnitExtension.class }) //拡張の登録
public class Sandbox03Test {

	@Test
	@SetUpDataSet // 事前データ
	@AssertDataSet // 事後データ
	void test01() throws Exception {
		new SampleService().execute();
	}

	@Test
	void test02(MyDbUnitSupport support, IDatabaseTester dbTester) {
		System.out.println(support);
		System.out.println(dbTester);
	}

	@Nested
	class Sandbox03NestedTest {

		@Test
		void test01(MyDbUnitSupport support) {
			System.out.println(support);
		}
	}
}
