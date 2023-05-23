package epsilongtmyon.db.entity;

import java.math.BigInteger;
import java.util.Date;

import epsilongtmyon.db.entity.common.AbstractEntity;
import epsilongtmyon.db.entity.common.AbstractEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@EntityListeners(AbstractEntityListener.class)
@Table(name = "DATE_SANDBOX2")
public class DateSandbox2 extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEQ")
	private BigInteger seq;

	@Temporal(TemporalType.DATE)
	@Column(name = "TIMESTAMP_DATE01")
	private Date timestampDate01;

	@Temporal(TemporalType.TIME)
	@Column(name = "TIMESTAMP_DATE02")
	private Date timestampDate02;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIMESTAMP_DATE03")
	private Date timestampDate03;

	public BigInteger getSeq() {
		return seq;
	}

	public void setSeq(BigInteger seq) {
		this.seq = seq;
	}

	public Date getTimestampDate01() {
		return timestampDate01;
	}

	public void setTimestampDate01(Date timestampDate01) {
		this.timestampDate01 = timestampDate01;
	}

	public Date getTimestampDate02() {
		return timestampDate02;
	}

	public void setTimestampDate02(Date timestampDate02) {
		this.timestampDate02 = timestampDate02;
	}

	public Date getTimestampDate03() {
		return timestampDate03;
	}

	public void setTimestampDate03(Date timestampDate03) {
		this.timestampDate03 = timestampDate03;
	}

	@Override
	public String toString() {
		return "DateSandbox2 [seq=" + seq + ", timestampDate01=" + timestampDate01 + ", timestampDate02="
				+ timestampDate02 + ", timestampDate03=" + timestampDate03 + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
