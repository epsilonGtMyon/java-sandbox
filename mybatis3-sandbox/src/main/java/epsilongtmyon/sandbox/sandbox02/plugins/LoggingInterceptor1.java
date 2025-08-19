package epsilongtmyon.sandbox.sandbox02.plugins;

import java.sql.Statement;
import java.util.Arrays;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({
	// 対象を複数指定できる。
//	@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),

//	@Signature(type = ParameterHandler.class, method = "getParameterObject", args = {  }),
//	@Signature(type = ParameterHandler.class, method = "setParameters", args = { PreparedStatement.class }),
	
//	@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }),
	
	@Signature(type = StatementHandler.class, method = "update", args = { Statement.class })
	
})
public class LoggingInterceptor1 implements Interceptor {

	private static Logger logger = LoggerFactory.getLogger(LoggingInterceptor1.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//Thread.dumpStack();

		logger.info("begin");
		logger.info("target = {}", invocation.getTarget());
		logger.info("method = {}", invocation.getMethod());
		logger.info("args = {}", Arrays.toString(invocation.getArgs()));

		Object returnObject = invocation.proceed();
		logger.info("end {}", returnObject);

		return returnObject;
	}

}
