package epsilongtmyon.spec.section.section05;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

import epsilongtmyon.spec.common.validation.annotation.Required;

public class Section05Bean02 {

	// ---

	@Required
	private String myCode01;

	@Required(groups = { Section05G1.class })
	private String myCode02;

	@Required(groups = { Section05G2.class })
	private String myCode03;

	public String getMyCode01() {
		return myCode01;
	}

	public void setMyCode01(String myCode01) {
		this.myCode01 = myCode01;
	}

	public String getMyCode02() {
		return myCode02;
	}

	public void setMyCode02(String myCode02) {
		this.myCode02 = myCode02;
	}

	public String getMyCode03() {
		return myCode03;
	}

	public void setMyCode03(String myCode03) {
		this.myCode03 = myCode03;
	}

	//-----------------
	// groups
	// グループは継承関係も作れる

	public interface Section05G1 {
	}

	public interface Section05G2 {
	}
	
	// 検証順序付き
	// 例えば基本的な型チェックを先にして、複雑な型チェックだったり、コストの高い検証をする場合などに使う
	// ほかのGroupSequenceも指定することができる。
	// 循環させてはいけない
	// このインターフェースはほかのインターフェースを継承しないで！
	// このインターフェースはgroupsの指定には使わないで！
	// https://jakarta.ee/specifications/bean-validation/3.1/jakarta-validation-spec-3.1.html#constraintdeclarationvalidationprocess-groupsequence-groupsequence
	@GroupSequence({ Section05G2.class, Section05G1.class, Default.class })
	public interface Section05Order { 

	}
}
