

--drop user 'frgconfdbtestusr'@'%';
--flush privileges;

create user 'frgconfdbtestusr'@'%' identified by 'frgconfdbtestpsw';

grant all on fragnostic_conf_db_test.* to 'frgconfdbtestusr'@'%' with grant option;
flush privileges;

