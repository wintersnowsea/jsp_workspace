create table reactnotice(
	reactnotice_idx number primary key
	, title varchar2(100)
	, writer varchar2(30)
	, content clob
	, regdate date default sysdate
	, hit number default 0
);

create sequence seq_reactnotice
increment by 1
start with 1;