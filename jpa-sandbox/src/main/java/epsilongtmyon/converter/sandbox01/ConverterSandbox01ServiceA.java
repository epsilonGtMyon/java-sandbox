package epsilongtmyon.converter.sandbox01;

import java.math.BigInteger;

import epsilongtmyon.db.entity.DateSandbox;
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

}
