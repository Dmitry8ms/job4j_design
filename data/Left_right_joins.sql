create database staff;
create table department (id serial primary key, name varchar(30));
create table employee (id serial primary key, name varchar(30));
alter table employee
add column dep_id int references department (id);

select * from employee;
select * from department;

insert into department (name) values ('Recruting dept.');
insert into department (name) values ('Development dept.');
insert into department (name) values ('Engineering dept.');
insert into department (name) values ('Legal dept.');

insert into employee (name, dep_id) values ('John', 1);
insert into employee (name, dep_id) values ('Ann', 1);
insert into employee (name, dep_id) values ('Santha', 2);
insert into employee (name, dep_id) values ('Sukayama', 2);
insert into employee (name, dep_id) values ('Gendalf', 2);
insert into employee (name, dep_id) values ('Toteschopidor', 4);

select * from employee e left join department d on e.dep_id = d.id;
select * from employee e right join department d on d.id = e.dep_id;
select * from department d left join employee e on d.id = e.dep_id;
select * from department d right join employee e on d.id = e.dep_id;
select * from employee e full join department d on e.dep_id = d.id;
select * from department d full join employee e on d.id = e.dep_id;
select * from employee e cross join department d;
select * from department d cross join employee e;

select d.name Отдел, e.name Работник from department d left join employee e on d.id = e.dep_id
where e.name is null;

select e.name Работник, d.name Отдел 
from employee e 
left join department d 
on e.dep_id = d.id;

select e.name Работник, d.name Отдел 
from department d 
right join employee e 
on d.id = e.dep_id;

create table teens (name varchar(30), gender varchar(1));
insert into teens (name, gender) values ('Bruce', 'm');
insert into teens (name, gender) values ('Maggie', 'f');
insert into teens (name, gender) values ('Andy', 'f');
insert into teens (name, gender) values ('Jason', 'm');
select * from teens;

select t1.name Мальчик, t1.gender, t2.name Девочка, t2.gender 
from teens t1 
cross join teens t2 
where t1.gender = 'm' and t1.gender <> t2.gender
order by t1.name;