create table board(
	board_idx number primary key
	, title varchar2(100)
	, writer varchar2(30)
	, content clob
	, regdate date default sysdate
	, hit number default 0
	, filename varchar2(30)
);

create sequence seq_board
increment by 1
start with 1;