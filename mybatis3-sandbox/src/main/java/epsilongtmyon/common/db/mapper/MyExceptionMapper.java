package epsilongtmyon.common.db.mapper;

import org.apache.ibatis.annotations.Insert;

import epsilongtmyon.common.db.entity.MyException;

public interface MyExceptionMapper {


	@Insert("""
			insert into MY_EXCEPTION (
			   EX_KEY
			  ,AMOUNT
			  ,CREATED_AT
			  ,UPDATED_AT
			) values (
			   #{exKey, jdbcType=VARCHAR}
			  ,#{amount, jdbcType=NUMERIC}
			  ,#{createdAt, jdbcType=TIMESTAMP}
			  ,#{updatedAt, jdbcType=TIMESTAMP}
			)
						""")
	int insert(MyException myException);
}
