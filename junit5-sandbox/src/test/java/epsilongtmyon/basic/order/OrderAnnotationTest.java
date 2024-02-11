package epsilongtmyon.basic.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// テスト実行順序制御のテスト
// junit-platform.propertiesのjunit.jupiter.testmethod.order.defaultでデフォルトのソートオーダーを指定できる

// Orderアノテーションで実行順序を制御する。
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderAnnotationTest {

	@Test
	@DisplayName("Order_1")
	@Order(1)
	void test1() {
		// perform assertions against null values
	}

	@Test
	@DisplayName("Order_2")
	@Order(2)
	void test2() {
		// perform assertions against empty values
	}

	@Test
	@DisplayName("Order_3")
	@Order(3)
	void test3() {
		// perform assertions against valid values
	}

	//-------------
	//

	@Test
	@DisplayName("Order_default-1")
	@Order(Order.DEFAULT - 1)
	void test_default_minus1() {
		// perform assertions against valid values
	}

	@Test
	@DisplayName("Order_None")
	void test_None() {
		// perform assertions against valid values
	}


	@Test
	@DisplayName("Order_default+1")
	@Order(Order.DEFAULT + 1)
	void test_default_plus1() {
		// perform assertions against valid values
	}

	@Test
	@DisplayName("Order_Max")
	@Order(Integer.MAX_VALUE)
	void testMax() {
		// perform assertions against valid values
	}
}
