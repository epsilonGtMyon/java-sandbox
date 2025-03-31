-- テストで使うテーブルやデータなど

create table TEST_TABLE(
   KEY01  varchar(10)
  ,COL01  varchar(10)
  ,constraint PK_TEST_TABLE primary key(
    KEY01
  )
)
;


insert into TEST_TABLE (KEY01, COL01) values ('K001', 'V001');
insert into TEST_TABLE (KEY01, COL01) values ('K002', 'V002');
insert into TEST_TABLE (KEY01, COL01) values ('K003', 'V003');

