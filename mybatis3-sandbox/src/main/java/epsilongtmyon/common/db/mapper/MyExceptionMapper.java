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
			   #{exKey}
			  ,#{amount}
			  ,#{createdAt}
			  ,#{updatedAt}
			)
						""")
	int insert(MyException myException);
}
