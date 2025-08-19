package epsilongtmyon.sandbox.sandbox02.plugins;

// 共通列の設定
import java.sql.Timestamp;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import epsilongtmyon.common.db.entity.AbstractEntity;

@Intercepts({
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
})
public class CommonFieldInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		Object arg = args[1];
		if (arg instanceof AbstractEntity entity) {
			MappedStatement stmt = (MappedStatement) args[0];
			SqlCommandType sqlCommandType = stmt.getSqlCommandType();

			Timestamp now = new Timestamp(System.currentTimeMillis());

			if (sqlCommandType == SqlCommandType.INSERT) {
				entity.setCreatedAt(now);
				entity.setUpdatedAt(now);

			} else if (sqlCommandType == SqlCommandType.UPDATE) {
				entity.setUpdatedAt(now);
			}
		}

		Object returnObject = invocation.proceed();
		return returnObject;
	}

}
