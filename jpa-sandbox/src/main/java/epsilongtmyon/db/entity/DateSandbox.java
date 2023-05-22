package epsilongtmyon.db.entity;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import epsilongtmyon.db.entity.common.AbstractEntity;
import epsilongtmyon.db.entity.common.AbstractEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AbstractEntityListener.class)
@Table(name = "DATE_SANDBOX")
public class DateSandbox extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private BigInteger seq;

	@Column(name = "TEXT_DATE01")
	private Date textDate01;

	@Column(name = "TEXT_DATE02")
	private LocalDate textDate02;

	@Column(name = "TIMESTAMP_DATE01")
	private String timestampDate01;

	@Column(name = "TIMESTAMP_DATE02")
	private LocalDateTime timestampDate02;

	public BigInteger getSeq() {
		return seq;
	}

	public void setSeq(BigInteger seq) {
		this.seq = seq;
	}

	public Date getTextDate01() {
		return textDate01;
	}

	public void setTextDate01(Date textDate01) {
		this.textDate01 = textDate01;
	}

	public LocalDate getTextDate02() {
		return textDate02;
	}

	public void setTextDate02(LocalDate textDate02) {
		this.textDate02 = textDate02;
	}

	public String getTimestampDate01() {
		return timestampDate01;
	}

	public void setTimestampDate01(String timestampDate01) {
		this.timestampDate01 = timestampDate01;
	}

	public LocalDateTime getTimestampDate02() {
		return timestampDate02;
	}

	public void setTimestampDate02(LocalDateTime timestampDate02) {
		this.timestampDate02 = timestampDate02;
	}

	@Override
	public String toString() {
		return "DateSandbox [seq=" + seq + ", textDate01=" + textDate01 + ", textDate02=" + textDate02
				+ ", timestampDate01=" + timestampDate01 + ", timestampDate02=" + timestampDate02 + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
