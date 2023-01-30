--MySQL
create table category(
	category_idx int primary key auto_increment 
	, category_name varchar(20)
) default character set utf8;

insert into category(category_name) values('한식');
insert into category(category_name) values('양식');
insert into category(category_name) values('일식');
insert into category(category_name) values('중식');


----------------------------------------------------
create table store(
	store_idx int primary key auto_increment
	, category_idx int
	, lati double default 0
	, longi double default 0
	, store_name varchar(30)
	, constraint fk_category_store foreign key(category_idx)
		references category(category_idx)
) default character set utf8;