package epsilongtmyon.basic.nested;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import epsilongtmyon.Calculator;

// 入れ子のテスト
// @Nestedはstatic classはだめ
// 親のBeforeEach, AfterEachは子供のテスト毎にも実行される。

public class NestedTest {

	@BeforeAll
	static void beforeAll() {
		System.out.println("root_beforeAll=========");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("root_beforeEach---");
	}

	@Test
	void root_test1() throws Exception {
		System.out.println("root_tes1t");
	}

	@Test
	void root_test2() throws Exception {
		System.out.println("root_test2");
	}

	// root_beforeEachはtest毎に実行される。
	// 共通の条件とかは親側でするのがいいのかな
	@Nested //static classはダメ
	class AddTest {

		private Calculator calc;

		// java16以降だと普通に実行される
		// そうでない場合は　@TestInstance(Lifecycle.PER_CLASS)が必要(どこに？)
		@BeforeAll
		static void beforeAll() {
			System.out.println("[add]add_beforeAll========");
		}

		@BeforeEach
		void beforeEach() {
			calc = new Calculator();
			System.out.println("[add]add_beforeEach---");
		}

		@Test
		void add_test1() throws Exception {
			System.out.println("[add]add_test1");
			assertEquals(3, calc.add(1, 2));
		}

		@Test
		void add_test2() throws Exception {
			System.out.println("[add]add_test2");
			assertEquals(5, calc.add(2, 3));
		}
	}

	@Nested
	class MinusTest {

		private Calculator calc;

		@BeforeAll
		static void beforeAll() {
			System.out.println("[minus]minus_beforeAll========");
		}

		@BeforeEach
		void beforeEach() {
			calc = new Calculator();
			System.out.println("[minus]minus_beforeEach---");
		}

		@Test
		void minus_test1() throws Exception {
			System.out.println("[minus]minus_test1");
			assertEquals(-1, calc.minus(1, 2));
		}

		@Test
		void minus_test2() throws Exception {
			System.out.println("[minus]minus_test2");
			assertEquals(-1, calc.minus(2, 3));
		}
	}
}
