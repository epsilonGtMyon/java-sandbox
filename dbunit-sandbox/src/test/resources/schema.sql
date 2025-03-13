create table if not exists TEST_TABLE (
   KEY1           varchar(5)
  ,KEY2           varchar(5)
  ,VARCHAR_COL    varchar(5)
  ,NUMBER_COL     number(10, 2)
  ,TIMESTAMP_COL  timestamp
  ,constraint PK_TEST_TABLE primary key(
     KEY1
    ,KEY2
  )
)
;

create table if not exists TEST_TABLE02 (
   KEY1           varchar(5)
  ,KEY2           varchar(5)
  ,VARCHAR_COL    varchar(5)
  ,NUMBER_COL     number(10, 2)
  ,TIMESTAMP_COL  timestamp
  ,constraint PK_TEST_TABLE02 primary key(
     KEY1
    ,KEY2
  )
)
;
