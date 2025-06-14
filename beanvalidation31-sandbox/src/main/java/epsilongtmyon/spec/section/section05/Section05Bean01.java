package epsilongtmyon.spec.section.section05;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import epsilongtmyon.spec.common.validation.annotation.HalfNum;
import epsilongtmyon.spec.common.validation.annotation.MaxLength;

public class Section05Bean01 {

	@Valid
	private Section05Bean01A nestedValue;

	// listValueにつけるのは前からあるやり方、Listの中につける
	private List<@Valid Section05Bean01A> listValue;

	private Optional<@Valid Section05Bean01A> optionalValue;

	public Section05Bean01A getNestedValue() {
		return nestedValue;
	}

	public void setNestedValue(Section05Bean01A nestedValue) {
		this.nestedValue = nestedValue;
	}

	public List<Section05Bean01A> getListValue() {
		return listValue;
	}

	public void setListValue(List<Section05Bean01A> listValue) {
		this.listValue = listValue;
	}

	public Optional<Section05Bean01A> getOptionalValue() {
		return optionalValue;
	}

	public void setOptionalValue(Optional<Section05Bean01A> optionalValue) {
		this.optionalValue = optionalValue;
	}

	public static class Section05Bean01A {

		@HalfNum
		@MaxLength(length = 5)
		private String myCode;

		public Section05Bean01A(@HalfNum @MaxLength(length = 5) String myCode) {
			super();
			this.myCode = myCode;
		}

		public String getMyCode() {
			return myCode;
		}

		public void setMyCode(String myCode) {
			this.myCode = myCode;
		}

	}
}
