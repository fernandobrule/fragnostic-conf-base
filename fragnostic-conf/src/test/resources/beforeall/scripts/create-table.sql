
-- ##############################################################
-- Tabla: TEST_TABLE
-- ##############################################################
create table if not exists fragnostic_conf_db_test.fragnostic_conf(
   frg_conf_id                    bigint unsigned not null auto_increment,
   frg_conf_key                   varchar(32) not null,
   frg_conf_value                 varchar(32) not null,
   constraint fragnostic_conf_pk  primary key(frg_conf_id),
   unique key frg_conf_key_uk     (frg_conf_key)
) engine = innodb default charset=latin1;
