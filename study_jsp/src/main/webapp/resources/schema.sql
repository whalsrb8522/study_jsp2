-- 2023-05-12 : member 테이블 생성
create table member (
	id varchar(20) NOT NULL PRIMARY KEY,
	password varchar(20) NOT NULL,
	name varchar(20) NOT NULL,
	email varchar(50),
	phone int,
	regdate datetime NOT NULL DEFAULT NOW(),
	lastlogin datetime
);