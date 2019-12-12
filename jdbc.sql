create database jdbc;

use jdbc;
create table hero(
id int auto_increment primary key,
adi varchar(50),
soyadi varchar(50)
)

select * from hero

create table movie(
id int auto_increment primary key,
heroId int;
filmAdi varchar(100),
budget decimal(13,2)
)

select * from movie
