-- ====================================
-- TABLE
-- ====================================

-- EMP
create table EMP (
   EMP_ID      varchar(6)  not null
  ,FIRST_NAME  varchar(10) not null
  ,FAMILY_NAME varchar(10) not null
  ,BLOOD_TYPE  varchar(2)  not null
  ,NOTE        varchar(1000)
  ,CREATED_AT  TIMESTAMP   not null
  ,constraint PK_EMP primary key(
     EMP_ID
  )
)
;

comment on table  EMP  is '社員';
comment on column EMP.EMP_ID  is '社員ID';


-- ORDER_HEADER
create table ORDER_HEADER (
   ORDER_ID       bigint identity
  ,CUSTOMER_NAME  varchar(30)

  ,constraint PK_ORDER_HEADER primary key (
     ORDER_ID
  )
);

comment on table  ORDER_HEADER               is '注文ヘッダ';
comment on column ORDER_HEADER.ORDER_ID      is '注文ID';
comment on column ORDER_HEADER.CUSTOMER_NAME is 'お客様名';


-- ORDER_DETAIL
create table ORDER_DETAIL (
   ORDER_ID          bigint
  ,ORDER_DETAIL_NO   integer
  ,PRODUCT_NAME      varchar(100)
  ,PRODUCT_COUNT     integer
  ,TOTAL_AMOUNT      decimal(15, 2)
  ,constraint PK_ORDER_DETAIL primary key (
      ORDER_ID
     ,ORDER_DETAIL_NO
  )
);

comment on table  ORDER_DETAIL                 is '注文明細';
comment on column ORDER_DETAIL.ORDER_ID        is '注文ID';
comment on column ORDER_DETAIL.ORDER_DETAIL_NO is '注文明細番号';
comment on column ORDER_DETAIL.PRODUCT_NAME    is '商品名';
comment on column ORDER_DETAIL.PRODUCT_COUNT   is '商品数';
comment on column ORDER_DETAIL.TOTAL_AMOUNT    is '合計金額';

-- ===========================================


alter table ORDER_DETAIL add constraint FK_ORDER_DETAIL_01 foreign key (
  ORDER_ID
) references ORDER_HEADER (
  ORDER_ID
);

