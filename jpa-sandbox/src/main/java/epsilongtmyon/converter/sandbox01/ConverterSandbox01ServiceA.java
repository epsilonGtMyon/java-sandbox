package epsilongtmyon.converter.sandbox01;

import java.math.BigInteger;
import java.util.Date;

import epsilongtmyon.db.entity.DateSandbox;
import epsilongtmyon.db.entity.DateSandbox2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ConverterSandbox01ServiceA {

	private final EntityManager em;

	public ConverterSandbox01ServiceA(EntityManager em) {
		super();
		this.em = em;
	}

	public void execute() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			executeInternal01();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	private void executeInternal01() {
		
		DateSandbox d = em.find(DateSandbox.class, new BigInteger("1"));
		
		// 日付と文字列の変換はいい感じにされてる
		// どこの仕様でそうなってるかは確認はこれから..
		System.out.println(d);

	}

	private void executeInternal02() {
		
		DateSandbox2 d = new DateSandbox2();
		
		Date now = new Date();
		d.setTimestampDate01(now);
		d.setTimestampDate02(now);//ここはDB側をtimeにしとかんとあかんかな
		d.setTimestampDate03(now);

		em.persist(d);
	}

	private void executeInternal03() {
		
		DateSandbox2 d = em.find(DateSandbox2.class, new BigInteger("1"));

		// @Temporal(TemporalType.DATE) をつけてると
		// DBの値に時分秒がはいってても0になっているみたい
		System.out.println(d);
	}

}
