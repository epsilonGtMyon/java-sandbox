drop table if exists MY_TABLE;
drop table if exists MY_LOG;
drop table if exists MY_EXCEPTION;
drop table if exists MY_RAWSQL;


create table MY_TABLE (
   ID             bigint auto_increment not null
  ,STRING_COL     varchar(100)
  ,BIGINT_COL     bigint
  ,INTEGER_COL    integer
  ,BIGDECIMAL_COL decimal(10, 2)
  ,DATE_COL       date
  ,TIMESTAMP_COL  timestamp
  ,CREATED_AT     timestamp
  ,UPDATED_AT     timestamp
  ,constraint PK_MY_TABLE primary key (
	ID
  )
);

create table MY_LOG (
   SEQ            bigint auto_increment not null
  ,LOG_MESSAGE    varchar(2000)
  ,CREATED_AT     timestamp
  ,UPDATED_AT     timestamp
  ,constraint PK_MY_LOG primary key(
	SEQ
  )
);

create table MY_EXCEPTION (
   EX_KEY         varchar(5)
  ,AMOUNT         decimal(10, 2)
  ,CREATED_AT     timestamp
  ,UPDATED_AT     timestamp
  ,constraint PK_MY_EXCEPTION primary key(
	EX_KEY
  )
);


create table MY_RAWSQL (
   ID             bigint auto_increment not null
  ,COL1           varchar(10)
  ,COL2           decimal(10, 2)
  ,CREATED_AT     timestamp
  ,UPDATED_AT     timestamp
  ,constraint PK_MY_RAWSQL primary key(
	ID
  )
);

-- =============================

insert into MY_TABLE (STRING_COL, BIGINT_COL, INTEGER_COL, BIGDECIMAL_COL, DATE_COL, TIMESTAMP_COL, CREATED_AT, UPDATED_AT)
  values ('あいうえお', 2000000, 5000, 12345.67, '2025-01-02', '2025-01-02 03:04:05', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into MY_TABLE (STRING_COL, BIGINT_COL, INTEGER_COL, BIGDECIMAL_COL, DATE_COL, TIMESTAMP_COL, CREATED_AT, UPDATED_AT)
  values ('かきくけこ', 3000000, 6000, 123456.78, '2026-01-02', '2026-01-02 03:04:05', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 
insert into MY_EXCEPTION (EX_KEY, AMOUNT, CREATED_AT, UPDATED_AT)
  values ('EX001', 10.01, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into MY_RAWSQL (
  COL1
 ,COL2
 ,CREATED_AT
 ,UPDATED_AT
) 
with recursive
  SERIES(SEQ, TEXT) as (
    select 1, '値' || 1
    union all
    select SEQ + 1, '値' || (SEQ + 1) from SERIES where SEQ < 20
  )
select
  TEXT
 ,SEQ
 ,CURRENT_TIMESTAMP
 ,CURRENT_TIMESTAMP
from SERIES
;
