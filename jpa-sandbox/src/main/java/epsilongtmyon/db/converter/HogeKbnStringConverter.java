package epsilongtmyon.db.converter;

import epsilongtmyon.db.vo.HogeKbn;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * {@link HogeKbn} と文字列を相互変換するコンバーター
 * @author epsil
 *
 */
@Converter
public class HogeKbnStringConverter implements AttributeConverter<HogeKbn, String> {

	@Override
	public String convertToDatabaseColumn(HogeKbn hogeKbn) {
		//		Thread.dumpStack();
		return hogeKbn == null ? null : hogeKbn.value;
	}

	@Override
	public HogeKbn convertToEntityAttribute(String value) {
		//		Thread.dumpStack();
		return HogeKbn.of(value);
	}

}
