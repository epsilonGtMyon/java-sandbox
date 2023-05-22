package epsilongtmyon.db.entity;

import epsilongtmyon.db.entity.common.AbstractEntity;
import epsilongtmyon.db.entity.common.AbstractEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

// NamedQueryでJPQLのクエリに名前を付けることができる。
// 起動時にプリコンパイルされて文法ミスにも気づけるそうだ
// xmlにも記述可能
@NamedQueries(@NamedQuery(name = "Emp.findByBloodType", query = "SELECT e FROM Emp e WHERE e.bloodType = :bloodType"))
@EntityListeners(AbstractEntityListener.class)
@Entity
@Table(name = "EMP")
public class Emp extends AbstractEntity {

	@Id
	@Column(name = "EMP_ID")
	private String empId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "FAMILY_NAME")
	private String familyName;

	@Column(name = "BLOOD_TYPE")
	private String bloodType;

	@Column(name = "NOTE")
	private String note;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", firstName=" + firstName + ", familyName=" + familyName + ", bloodType="
				+ bloodType + ", note=" + note + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
