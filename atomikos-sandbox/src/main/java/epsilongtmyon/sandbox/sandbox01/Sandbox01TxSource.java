package epsilongtmyon.sandbox.sandbox01;

import javax.sql.DataSource;

import jakarta.transaction.TransactionManager;

import org.h2.jdbcx.JdbcDataSource;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;

public class Sandbox01TxSource implements AutoCloseable {

	private UserTransactionManager utm;
	
	private AtomikosDataSourceBean adBean1;
	
	private AtomikosDataSourceBean adBean2;
	
	// この辺
	// https://www.atomikos.com/Documentation/GettingStartedWithTransactionsEssentials
	// https://www.atomikos.com/Documentation/ConfiguringJdbc
	public void init() {

		utm = new UserTransactionManager();
				
		JdbcDataSource ds1 = new JdbcDataSource();
		ds1.setUrl("jdbc:h2:tcp://localhost/xstest1");
		ds1.setUser("sa");
		ds1.setPassword("");
		adBean1 = new AtomikosDataSourceBean();
		adBean1.setUniqueResourceName("h2-1"); 
		adBean1.setXaDataSource(ds1);
		adBean1.setPoolSize ( 5 );
		
		// ---

		JdbcDataSource ds2 = new JdbcDataSource();
		ds2.setUrl("jdbc:h2:tcp://localhost:19092/xstest2");
		ds2.setUser("sa");
		ds2.setPassword("");
		adBean2 = new AtomikosDataSourceBean();
		adBean2.setUniqueResourceName("h2-2"); 
		adBean2.setXaDataSource(ds2);
		adBean2.setPoolSize ( 5 );
	}
	
	public TransactionManager getTransactionManager() {
		return utm;
	}
	
	public DataSource getDataSource1() {
		return adBean1;
	}

	public DataSource getDataSource2() {
		return adBean2;
	}

	@Override
	public void close() throws Exception {
		utm.close();
		
		adBean1.close();
		adBean2.close();
	}
}
