package epsilongtmyon.sandbox.sandbox01;

import epsilongtmyon.db.entity.Emp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class Sandbox01ServiceA {

	private final EntityManager em;

	public Sandbox01ServiceA(EntityManager em) {
		this.em = em;
	}

	public void execute() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			executeInternal03();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	private void executeInternal01() {

		Emp em01 = em.find(Emp.class, (Object) "E0001");

		System.out.println(em01);

	}

	/**
	 * 参照
	 */
	private void executeInternal02() {

		// 参照だけ取得
		Emp emp = em.getReference(Emp.class, "E0001");
		System.out.println("------");
		System.out.println(emp.getEmpId());
		System.out.println("------");
		// ↓ここで実際のSQLが実行される。(Proxy経由)
		System.out.println(emp.getFirstName());

		// 存在しない条件で検索
		Emp emp2 = em.getReference(Emp.class, "E0009");
		// ↓ここで例外がおきる
		System.out.println(emp2.getFirstName());

	}

	private void executeInternal03() {
		String maxEmpId = findMaxEmpId();

		int intMaxEmpId = maxEmpId == null ? 0 : Integer.parseInt(maxEmpId.substring(1));
		final String nextEmpId = "E" + String.format("%04d", intMaxEmpId + 1);

		//--------------------

		final Emp emp = new Emp();
		emp.setEmpId(nextEmpId);
		emp.setFirstName("三郎");
		emp.setFamilyName("中村");
		emp.setBloodType("AB");
		//emp.setNote("あいやー");
		// emp.setCreatedAt(new Timestamp(System.currentTimeMillis()));

		// 追加
		em.persist(emp);

		// 更新もしくは追加
		//		final Emp mergedEmp = em.merge(emp);
		//		mergedEmp.setBloodtype("AB");

	}

	/**
	 * 削除(失敗)
	 */
	private void executeInternal04Fail() {
		final Emp emp = new Emp();
		emp.setEmpId("E0001");

		// 削除
		em.remove(emp);
	}

	/**
	 * 削除
	 */
	private void executeInternal04() {
		// 冗長、もっといいやり方あるはず
		String maxEmpId = findMaxEmpId();

		Emp emp = em.find(Emp.class, (Object) maxEmpId);

		// 削除
		em.remove(emp);
	}

	private String findMaxEmpId() {
		String sql = """
				select
				  MAX(EMP_ID)
				from
				  EMP
								""";
		Query query = em.createNativeQuery(sql, String.class);
		String maxEmpId = (String) query.getSingleResult();
		return maxEmpId;
	}
}
