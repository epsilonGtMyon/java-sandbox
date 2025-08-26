package epsilongtmyon.sandbox.sandbox05;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import epsilongtmyon.common.db.entity.MyRawsql;

public interface Sandbox05Mapper {

	@Select("${rawSql}")
	List<MyRawsql> selectRaw(@Param("rawSql")String rawSql, @Param("param")Map<String, Object> param);
}
