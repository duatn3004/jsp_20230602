use mysql;

create user 'jspuser'@'localhost' identified by 'mysql';
grant all privileges on jspdb.* to 'jspuser'@'localhost' with grant option;
flush privileges;

create database jspdb;

-- 2023-05-12
create table member(
id varchar(100),
password varchar(100),
email varchar(100),
age int,
reg_date datetime default now(),
last_login datetime default null,
primary key(id));

-- 2023-05-16
create table board(
bno int not null auto_increment,
title varchar(100) not null,
writer varchar(100) not null,
content text,
reg_date datetime default now(),  => current_timestamp
primary key(bno));


-- 2023-05-17
alter table board add column cnt int default 0;

-- 2023-05-19
create table comment(
cno int not null auto_increment,
bno int default 0,
writer varchar(100) default “unknown”,
content varchar(1000) not null,
reg_date datetime default now(),
primary key(cno));

-- 2023-05-25
alter table board add image_file text; 

);