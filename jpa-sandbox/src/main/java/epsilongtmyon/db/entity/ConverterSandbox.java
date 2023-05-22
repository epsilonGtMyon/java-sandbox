package epsilongtmyon.db.entity;

import java.math.BigInteger;

import epsilongtmyon.db.converter.HogeKbnStringConverter;
import epsilongtmyon.db.entity.common.AbstractEntity;
import epsilongtmyon.db.entity.common.AbstractEntityListener;
import epsilongtmyon.db.vo.HogeKbn;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AbstractEntityListener.class)
@Table(name = "CONVERTER_SANDBOX")
public class ConverterSandbox extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private BigInteger seq;

	@Convert(converter = HogeKbnStringConverter.class)
	@Column(name = "TEXT_ENUM_VALUE01")
	private HogeKbn textEnumValue01;

	public BigInteger getSeq() {
		return seq;
	}

	public void setSeq(BigInteger seq) {
		this.seq = seq;
	}

	public HogeKbn getTextEnumValue01() {
		return textEnumValue01;
	}

	public void setTextEnumValue01(HogeKbn textEnumValue01) {
		this.textEnumValue01 = textEnumValue01;
	}

	@Override
	public String toString() {
		return "ConverterSandbox [seq=" + seq + ", textEnumValue01=" + textEnumValue01 + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
