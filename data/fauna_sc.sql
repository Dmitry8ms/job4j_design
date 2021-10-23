create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
select * from fauna;
insert into fauna(name, avg_age, discovery_date) values(
			'Zubatik polosaty', 10001, '1949-10-1');
insert into fauna(name, avg_age, discovery_date) values(
			'Zubatik zubaty', 12000, '1949-10-1');
insert into fauna(name, avg_age, discovery_date) values(
			'Rusty saw fish', 20000, '1920-10-5');
insert into fauna(name, avg_age, discovery_date) values(
			'Fish of fucking glory', 50000, '1900-1-1');
insert into fauna(name, avg_age, discovery_date) values(
			'Transgender groundhog', 5000, null);
select * from fauna where name ilike '%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-1-1';
create table scientist (
id serial primary key,
name varchar(50)
);
alter table fauna
add column scientist_id int references scientist(id);
insert into scientist (name) values('Ivan Ivanov');
insert into scientist (name) values('Vasiliy Vasiliev');
insert into scientist (name) values('William Smith');
select * from scientist;
update fauna set scientist_id = 2 where id = 1;
update fauna set scientist_id = 2 where id = 2;
update fauna set scientist_id = 1 where id = 3;
update fauna set scientist_id = 2 where id = 4;
update fauna set scientist_id = 3 where id = 5;
select * from fauna join scientist sc on fauna.scientist_id = sc.id;
select fauna.name, sc.name
from fauna 
join scientist sc 
on fauna.scientist_id = sc.id;
select f.name as Вид, f.discovery_date Дата_открытия, sc.name Имя_ученого
from fauna f
join scientist sc 
on f.scientist_id = sc.id;
select f.name as Вид, f.discovery_date Дата_открытия, sc.name Имя_ученого
from fauna f
join scientist sc 
on f.scientist_id = sc.id
order by discovery_date;