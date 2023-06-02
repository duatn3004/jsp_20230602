use mysql;

create user 'javauser'@'localhost' identified by 'mysql';
grant all privileges on javadb.* to 'javauser'@'localhost' with grant option;
flush privileges;

create database javadb;

-- 2023-05-17
create table member(
id varchar(100),
password varchar(100),
email varchar(100),
age int,
reg_date datetime default now(),
last_login datetime default null,
primary key(id));