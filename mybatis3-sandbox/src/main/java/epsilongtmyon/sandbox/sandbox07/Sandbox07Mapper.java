package epsilongtmyon.sandbox.sandbox07;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import epsilongtmyon.common.db.entity.MyTable;

public interface Sandbox07Mapper {

	@Select("""
			select
			   STRING_COL
			  ,BIGINT_COL
			  ,INTEGER_COL
			  ,BIGDECIMAL_COL
			from
			  MY_TABLE
			order by
			  ID
						""")
	List<MyTable> selectNull();
}
