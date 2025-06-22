package epsilongtmyon.sandbox.sandbox01.db.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Script;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.criteria.QueryDsl;

import epsilongtmyon.sandbox.sandbox01.db.entity.Sandbox01Entity;
import epsilongtmyon.sandbox.sandbox01.db.entity.Sandbox01Entity_;

@Dao
public interface Sandbox01Dao {

	@Script
	void createTables();

	@Insert
	int insert(Sandbox01Entity entity);

	@Update
	int update(Sandbox01Entity entity);
	


	// @SelectでもSQLファイルを用意せず@Sqlアノテーションで記述できる。
	@Select
	@Sql("""
select
  /*%expand*/*
from
  SANDBOX01
where
 KEY01 = /* key01 */'a'
and KEY02 = /* key02 */'b'
 """)
	Sandbox01Entity findByIds(String key01, String key02);

	default Sandbox01Entity findByIdsDsl(String key01, String key02) {
		Config config = Config.get(this);
		QueryDsl queryDsl = new QueryDsl(config);

		Sandbox01Entity_ s = new Sandbox01Entity_();

		return queryDsl
				// from はエンティティのメタモデル
				.from(s)

				.where(c -> {
					c.eq(s.key01, key01);
					c.eq(s.key02, key02);
				})

				.fetchOne();
	}

	default List<Sandbox01Entity> findByKey01Dsl(String key01) {
		Config config = Config.get(this);
		QueryDsl queryDsl = new QueryDsl(config);

		Sandbox01Entity_ s = new Sandbox01Entity_();

		return queryDsl
				// from はエンティティのメタモデル
				.from(s)

				.where(c -> {
					c.eq(s.key01, key01);
				})
				.orderBy(c -> {
					c.asc(s.key01);
				})
				.fetch();
	}
}
