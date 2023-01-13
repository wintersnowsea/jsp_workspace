--뉴스 : 제목, 작성자, 내용, 등록일, 조회수
--news : title, writer, content, regdate, hit
create table news(
	news_idx number primary key
	, title varchar2(100)
	, writer varchar2(30)
	, content clob
	, regdate date default sysdate
	, hit number default 0
);

create sequence seq_news
increment by 1
start with 1;

--댓글 : 댓글메세지, 작성자, 등록일
--comments : msg, author, writedate
--제약조건 fk_news_comments
create table comments(
	comments_idx number primary key
	, news_idx number
	, msg clob
	, author varchar(30)
	, writedate date default sysdate
	, constraint fk_news_comments foreign key (news_idx) references news (news_idx) 
);

create sequence seq_comments
increment by 1
start with 1;