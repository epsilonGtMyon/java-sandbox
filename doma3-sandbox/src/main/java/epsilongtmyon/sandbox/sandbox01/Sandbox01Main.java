package epsilongtmyon.sandbox.sandbox01;

import java.util.List;

import org.seasar.doma.jdbc.tx.LocalTransaction;
import org.seasar.doma.jdbc.tx.TransactionManager;

import epsilongtmyon.config.doma.MyDbConfig;
import epsilongtmyon.sandbox.sandbox01.db.dao.Sandbox01Dao;
import epsilongtmyon.sandbox.sandbox01.db.dao.Sandbox01DaoImpl;
import epsilongtmyon.sandbox.sandbox01.db.entity.Sandbox01Entity;

public class Sandbox01Main {

	private Sandbox01Dao sandbox01Dao = new Sandbox01DaoImpl(MyDbConfig.singleton());

	public static void main(String[] args) {
		var main = new Sandbox01Main();
		main.sandbox01Dao.createTables();
		main.start2();
	}

	private void start1() {

		// 昔ながらのLocalTransaction
		LocalTransaction tx = MyDbConfig.singleton().getLocalTransaction();
		try {
			tx.begin();

			Sandbox01Entity entity1 = new Sandbox01Entity();
			entity1.setKey01("A");
			entity1.setKey02("0001");
			entity1.setStrValue01("あいう");
			sandbox01Dao.insert(entity1);

			Sandbox01Entity entity2 = new Sandbox01Entity();
			entity2.setKey01("A");
			entity2.setKey02("0002");
			entity2.setStrValue01("かきく");
			sandbox01Dao.insert(entity2);

			tx.commit();
		} finally {
			tx.rollback();
		}
	}

	private void start2() {

		TransactionManager txManager = MyDbConfig.singleton().getTransactionManager();
		txManager.required(() -> {
			Sandbox01Entity entity1 = new Sandbox01Entity();
			entity1.setKey01("A");
			entity1.setKey02("0001");
			entity1.setStrValue01("あいう");
			sandbox01Dao.insert(entity1);

			Sandbox01Entity entity2 = new Sandbox01Entity();
			entity2.setKey01("A");
			entity2.setKey02("0002");
			entity2.setStrValue01("かきく");
			sandbox01Dao.insert(entity2);

			// ロールバックの予約
			// txManager.setRollbackOnly();
		});

		txManager.required(() -> {
			Sandbox01Entity r1 = sandbox01Dao.findByIds("A", "0001");
			System.out.println();
			System.out.println(r1);

			Sandbox01Entity r2 = sandbox01Dao.findByIdsDsl("A", "0001");
			System.out.println();
			System.out.println(r2);

			List<Sandbox01Entity> r3 = sandbox01Dao.findByKey01Dsl("A");
			System.out.println();
			r3.forEach(System.out::println);

		});
	}
}
