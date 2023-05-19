-- 2023-05-12 : member 테이블 생성
create database jspdb;

create table member (
	id varchar(20) NOT NULL PRIMARY KEY,
	password varchar(20) NOT NULL,
	name varchar(20) NOT NULL,
	email varchar(50),
	phone int,
	regdate datetime NOT NULL DEFAULT NOW(),
	lastlogin datetime
);

create user "jspuser"@"localhost" identified by "mysql";

grant all privileges on jspdb.* to "jspuser"@"localhost" with grant option;
flush privileges;

-- 2023.05.15 : member 테이블 auth 속성 추가, phone 속성 수정
alter table member add auth char(1) default = 0;
alter table member modify column phone varchar(20);

-- 2023.05.16 : board 테이블 생성
create table board (
	bno int AUTO_INCREMENT NOT NULL PRIMARY KEY,
	title varchar(30) NOT NULL,
	writer varchar(20) NOT NULL,
	regdate datetime NOT NULL DEFAULT NOW(),
	content text,
	readcount int default 0
);

-- 2023.05.19 : comment 테이블 생성 (댓글번호, 게시글번호, 작성자, 내용, 작성일자)
create table comment(
	cno int not null auto_increment,
	bno int not null default 0,
	writer varchar(20) not null default "익명",
	content text not null,
	regdate datetime default now(),
	primary key(cno)
);