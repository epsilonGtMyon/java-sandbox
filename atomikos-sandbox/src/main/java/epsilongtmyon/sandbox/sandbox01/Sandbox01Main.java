package epsilongtmyon.sandbox.sandbox01;

import java.sql.Connection;
import java.sql.Statement;

import jakarta.transaction.TransactionManager;


public class Sandbox01Main {

	private Sandbox01TxSource txSrc;

	public static void main(String[] args) throws Exception {
		var main = new Sandbox01Main();
		try (Sandbox01TxSource txSrc = new Sandbox01TxSource()) {
			txSrc.init();
			main.txSrc = txSrc;
			main.start1();
		}

	}

	
	// とりあえず雑に
	private void start1() throws Exception {

		TransactionManager tx = txSrc.getTransactionManager();

		tx.begin();
		try {
			try(Connection con = txSrc.getDataSource1().getConnection();
			Statement stmt = con.createStatement();) {
				stmt.execute("insert into LOG (LOG_TEXT) values ('データ1')");	
			}

			try(Connection con = txSrc.getDataSource2().getConnection();
			Statement stmt = con.createStatement();) {
				stmt.execute("insert into LOG (LOG_TEXT) values ('データ2')");	
			}
			
			if(true) {
				throw new Exception("aa");
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}

	}
}
