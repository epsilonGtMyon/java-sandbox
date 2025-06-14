package epsilongtmyon.spec.section.section06;

import jakarta.validation.Valid;

import epsilongtmyon.spec.common.validation.annotation.HalfNum;
import epsilongtmyon.spec.common.validation.annotation.MaxLength;
import epsilongtmyon.spec.common.validation.annotation.Required;

// メソッドのバリデーション検証用Bean

public class Section06Bean01 {

	public void method01(

			@Required @MaxLength(length = 3) String argValue01,

			@MaxLength(length = 5) String argValue02) {
	}

	public void method02(
			@Valid Section06Method02Arg param1) {

	}

	public static class Section06Method02Arg {

		@Required
		@MaxLength(length = 3)
		private String value01;

		@HalfNum
		@MaxLength(length = 3)
		private String vlaue02;

		public Section06Method02Arg(@Required @MaxLength(length = 3) String value01,
				@HalfNum @MaxLength(length = 3) String vlaue02) {
			super();
			this.value01 = value01;
			this.vlaue02 = vlaue02;
		}

		public String getValue01() {
			return value01;
		}

		public void setValue01(String value01) {
			this.value01 = value01;
		}

		public String getVlaue02() {
			return vlaue02;
		}

		public void setVlaue02(String vlaue02) {
			this.vlaue02 = vlaue02;
		}

	}

}
