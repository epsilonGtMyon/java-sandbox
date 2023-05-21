package epsilongtmyon.jpql.sandbox01.dto;

import java.io.Serializable;

public class JpqlSandbox01EmpDto implements Serializable {

	private String empId;

	private String firstName;

	private String familyName;

	public JpqlSandbox01EmpDto(String empId, String firstName, String familyName) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.familyName = familyName;
	}

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

	@Override
	public String toString() {
		return "JpqlSandbox01EmpDto [empId=" + empId + ", firstName=" + firstName + ", familyName=" + familyName + "]";
	}

}
