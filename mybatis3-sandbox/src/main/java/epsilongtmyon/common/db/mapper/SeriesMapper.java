package epsilongtmyon.common.db.mapper;

import java.math.BigInteger;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import epsilongtmyon.common.db.entity.Series;

public interface SeriesMapper {

	@Select("""
			with recursive
			  SERIES(SEQ, TEXT) as (
			    select 1, 'TEXT' || 1
			    union all
			    select SEQ + 1, 'TEXT' || (SEQ + 1) from SERIES where SEQ < #{max}
			  )
			select * from SERIES
						""")
	Cursor<Series> select(@Param("max") BigInteger max);
	
}
