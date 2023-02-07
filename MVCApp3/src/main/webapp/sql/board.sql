--use jspshop

create table board(
	board_idx int primary key auto_increment
	, title varchar(100)
	, writer varchar(20)
	, content text
	, regdate timestamp default now()
	, hit int default 0
) default character set utf8;


----------------------------------
--부서
create table dept(
	deptno int primary key auto_increment
	, dname varchar(20)
) default character set utf8;

--사원
create table emp(
	empno int primary key auto_increment
	, ename varchar(20)
	, sal int default 3200
	, deptno int
	, constraint fk_dept_emp foreign key(deptno)
		references dept(deptno)
) default character set utf8;