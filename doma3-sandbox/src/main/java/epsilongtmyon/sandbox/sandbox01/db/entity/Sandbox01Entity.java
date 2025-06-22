package epsilongtmyon.sandbox.sandbox01.db.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.Table;

import epsilongtmyon.sandbox.sandbox01.db.entity.listener.Sandbox01EntityListener;

// Criteria APIを使うためにmetamodelをつける
@Entity(listener = Sandbox01EntityListener.class, metamodel = @Metamodel)
@Table(name = "SANDBOX01")
public class Sandbox01Entity {

	@Id
	@Column(name = "KEY01")
	private String key01;

	@Id
	@Column(name = "KEY02")
	private String key02;

	@Column(name = "STR_VALUE01")
	private String strValue01;

	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	@OriginalStates
	Sandbox01Entity originalStates;

	public String getKey01() {
		return key01;
	}

	public void setKey01(String key01) {
		this.key01 = key01;
	}

	public String getKey02() {
		return key02;
	}

	public void setKey02(String key02) {
		this.key02 = key02;
	}

	public String getStrValue01() {
		return strValue01;
	}

	public void setStrValue01(String strValue01) {
		this.strValue01 = strValue01;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Sandbox01Entity [key01=" + key01 + ", key02=" + key02 + ", strValue01=" + strValue01 + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
