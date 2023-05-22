package epsilongtmyon.db.vo;

import java.util.Objects;

//検証用の適当なenum
public enum HogeKbn {

	A("001"),

	B("002"),

	;

	public final String value;

	private HogeKbn(String value) {
		this.value = value;
	}

	public static HogeKbn of(String value) {
		if (value == null) {
			return null;
		}

		for (HogeKbn hoge : values()) {
			if (Objects.equals(hoge.value, value)) {
				return hoge;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
