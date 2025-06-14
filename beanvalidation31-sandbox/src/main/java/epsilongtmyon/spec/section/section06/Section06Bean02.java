package epsilongtmyon.spec.section.section06;

import java.util.List;

import jakarta.validation.Valid;

import epsilongtmyon.spec.common.validation.annotation.HalfNum;
import epsilongtmyon.spec.common.validation.annotation.MaxLength;

// バリデーション結果検証用

public class Section06Bean02 {

	@HalfNum
	@MaxLength(length = 3)
	private String value01;

	@Valid
	private Section06Bean02A nestedBeanA;

	private List<@Valid Section06Bean02B> listBeansB;

	public String getValue01() {
		return value01;
	}

	public void setValue01(String value01) {
		this.value01 = value01;
	}

	public Section06Bean02A getNestedBeanA() {
		return nestedBeanA;
	}

	public void setNestedBeanA(Section06Bean02A nestedBeanA) {
		this.nestedBeanA = nestedBeanA;
	}

	public List<Section06Bean02B> getListBeansB() {
		return listBeansB;
	}

	public void setListBeansB(List<Section06Bean02B> listBeansB) {
		this.listBeansB = listBeansB;
	}

	@Override
	public String toString() {
		return "Section06Bean02 [value01=" + value01 + ", nestedBeanA=" + nestedBeanA + ", listBeansB=" + listBeansB
				+ "]";
	}

	public static class Section06Bean02A {

		@HalfNum
		@MaxLength(length = 3)
		private String aValue01;

		@HalfNum
		@MaxLength(length = 3)
		private String aValue02;

		public Section06Bean02A(
				String aValue01,
				String aValue02) {
			super();
			this.aValue01 = aValue01;
			this.aValue02 = aValue02;
		}

		public String getaValue01() {
			return aValue01;
		}

		public void setaValue01(String aValue01) {
			this.aValue01 = aValue01;
		}

		public String getaValue02() {
			return aValue02;
		}

		public void setaValue02(String aValue02) {
			this.aValue02 = aValue02;
		}

		@Override
		public String toString() {
			return "Section06Bean02A [aValue01=" + aValue01 + ", aValue02=" + aValue02 + "]";
		}

	}

	public static class Section06Bean02B {

		@HalfNum
		@MaxLength(length = 3)
		private String bValue01;

		@HalfNum
		@MaxLength(length = 3)
		private String bValue02;

		public Section06Bean02B(
				String bValue01,
				String bValue02) {
			super();
			this.bValue01 = bValue01;
			this.bValue02 = bValue02;
		}

		public String getbValue01() {
			return bValue01;
		}

		public void setbValue01(String bValue01) {
			this.bValue01 = bValue01;
		}

		public String getbValue02() {
			return bValue02;
		}

		public void setbValue02(String bValue02) {
			this.bValue02 = bValue02;
		}

		@Override
		public String toString() {
			return "Section06Bean02B [bValue01=" + bValue01 + ", bValue02=" + bValue02 + "]";
		}

	}
}
