package epsilongtmyon.common.db.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import epsilongtmyon.common.db.entity.MyLog;

public interface MyLogMapper {

	@Options(useGeneratedKeys = true, keyProperty = "seq", keyColumn = "SEQ")
	@Insert("""
			insert into MY_LOG (
			   LOG_MESSAGE
			  ,CREATED_AT
			  ,UPDATED_AT
			) values (
			   #{logMessage}
			  ,#{createdAt}
			  ,#{updatedAt}
			)
						""")
	int insert(MyLog myLog);

	@Update("""
			update MY_LOG set
			   LOG_MESSAGE = #{logMessage}
			  ,UPDATED_AT = #{updatedAt}
			where
			  SEQ = #{seq}
			""")
	int update(MyLog myLog);

	@Delete("""
			delete from MY_LOG
			where
			  SEQ = #{seq}
			""")
	int delete(MyLog myLog);

	@Delete("""
			delete from MY_LOG
			where
			  SEQ = #{seq}
			""")
	int deleteByKey(@Param("seq") BigInteger seq);

	@Select("""
			select
			   SEQ
			  ,LOG_MESSAGE
			  ,CREATED_AT
			  ,UPDATED_AT
			from
			  MY_LOG
			where
			  SEQ = #{seq}
			""")
	MyLog select(@Param("seq") BigInteger seq);

	@Select("""
			select
			   SEQ
			  ,LOG_MESSAGE
			  ,CREATED_AT
			  ,UPDATED_AT
			from
			  MY_LOG
			order by
			  SEQ
			""")
	List<MyLog> selectAll();
}
