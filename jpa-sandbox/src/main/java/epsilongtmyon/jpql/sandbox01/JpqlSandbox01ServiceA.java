package epsilongtmyon.jpql.sandbox01;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import epsilongtmyon.db.entity.Emp;
import epsilongtmyon.jpql.sandbox01.dto.JpqlSandbox01EmpDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class JpqlSandbox01ServiceA implements Serializable{

	private final EntityManager em;

	public JpqlSandbox01ServiceA(EntityManager em) {
		super();
		this.em = em;
	}

	public void execute() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			executeInternal04();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	private void executeInternal01() {

		// データベースではなくエンティティに対してのクエリなのでEMPではなくEmpにすること
		TypedQuery<Emp> query1 = em.createQuery("SELECT x FROM Emp x", Emp.class);
		List<Emp> emps1 = query1.getResultList();

		emps1.forEach(emp -> {
			System.out.println(emp);
		});

		System.out.println("---------------");

		// ID逆順
		TypedQuery<Emp> query2 = em.createQuery("SELECT x FROM Emp x ORDER BY x.empId DESC", Emp.class);
		List<Emp> emps2 = query2.getResultList();

		emps2.forEach(emp -> {
			System.out.println(emp);
		});

	}

	private void executeInternal02() {
		// 条件直書き
		TypedQuery<Emp> query1 = em.createQuery("SELECT x FROM Emp x WHERE x.empId = 'E0001'", Emp.class);
		List<Emp> emps1 = query1.getResultList();

		emps1.forEach(emp -> {
			System.out.println(emp);
		});
		
		//------------------------------
		System.out.println("------------------------------");

		// パラメータ指定
		TypedQuery<Emp> query2 = em.createQuery("SELECT x FROM Emp x WHERE x.empId = :empId", Emp.class);
		query2.setParameter("empId", "E0002");
		List<Emp> emps2 = query2.getResultList();

		emps2.forEach(emp -> {
			System.out.println(emp);
		});
		
		//------------------------------
		System.out.println("------------------------------");
		TypedQuery<Emp> query3 = em.createQuery("SELECT x FROM Emp x WHERE x.empId IN :empIds", Emp.class)
								.setParameter("empIds", List.of("E0001", "E0003"));
		
		List<Emp> emps3 = query3.getResultList();
		emps3.forEach(emp -> {
			System.out.println(emp);
		});

	}

	private void executeInternal03() {
		TypedQuery<Object[]> query1 = em.createQuery("SELECT x.empId, x.createdAt FROM Emp x ORDER BY x.empId", Object[].class);
		List<Object[]> result1 = query1.getResultList();
		result1.forEach(emp -> {
			System.out.println(Arrays.toString(emp));
		});
		
		//------------------------------
		System.out.println("------------------------------");
		
		// NEW 演算子を使って写像を得る
		final String jpql = """
SELECT
  NEW epsilongtmyon.jpql.sandbox01.dto.JpqlSandbox01EmpDto(x.empId, x.firstName, x.familyName)
FROM
  Emp x
ORDER BY
  x.empId
				""";
		TypedQuery<JpqlSandbox01EmpDto> query2 = em.createQuery(jpql, JpqlSandbox01EmpDto.class);
		List<JpqlSandbox01EmpDto> result2 = query2.getResultList();
		result2.forEach(emp -> {
			System.out.println(emp);
		});
	}
	
	private void executeInternal04() {
		
		final String jpql1 = """
SELECT
  e
FROM
  Emp e
WHERE
  e.familyName || e.firstName like :name escape '$'
				""";
		
		String paramName = "%中%";
		
		TypedQuery<Emp> query1 = em.createQuery(jpql1, Emp.class)
			.setParameter("name", paramName);
		
		try(Stream<Emp> emps1 = query1.getResultStream()){
			emps1.forEach(emp -> {
				System.out.println(emp);
			});
		}
	}
}
