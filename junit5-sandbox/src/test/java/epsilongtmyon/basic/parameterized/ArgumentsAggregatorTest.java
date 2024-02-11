package epsilongtmyon.basic.parameterized;

import java.time.LocalDate;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

// ArgumentsAggregatorと@AggregateWithで 値を集約した型を作れる
// @AggregateWith(PersonAggregator はこれを専用のアノテーションを作ることで繰り返しの指定をなくせる。
public class ArgumentsAggregatorTest {

	@ParameterizedTest
	@CsvSource({
			"Jane, Doe, F, 1990-05-20",
			"John, Doe, M, 1990-10-22"
	})
	void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
		System.out.println(person);
	}

	public static class Person {

		public String firstName;
		public String familyName;
		public String gendar;
		public LocalDate birthDay;

		public Person(String firstName, String familyName, String gendar, LocalDate birthDay) {
			this.firstName = firstName;
			this.familyName = familyName;
			this.gendar = gendar;
			this.birthDay = birthDay;
		}

		@Override
		public String toString() {
			return "Person [firstName=" + firstName + ", familyName=" + familyName + ", gendar=" + gendar
					+ ", birthDay=" + birthDay + "]";
		}

	}

	public static class PersonAggregator implements ArgumentsAggregator {
		@Override
		public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
			return new Person(arguments.getString(0),
					arguments.getString(1),
					arguments.getString(2),
					arguments.get(3, LocalDate.class));
		}
	}
}
