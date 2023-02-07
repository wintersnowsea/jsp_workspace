create table board(
	board_idx int primary key auto_increment
	, title varchar(100)
	, writer varchar(20)
	, content text
	, regdate timestamp default now()
	, hit int default 0
) default character set utf8;