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