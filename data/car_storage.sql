create database cars;
create table car (id serial primary key, 
				  name varchar(20), 
				  body_id int references body (id),
				  engine_id int references engine (id),
				  gear_id int references gear (id));
create table engine (id serial primary key, name varchar(20));
create table body (id serial primary key, name varchar(20));
create table gear (id serial primary key, name varchar(20));

insert into body (name) values ('body1');
insert into body (name) values ('body2');
insert into body (name) values ('body3');

insert into engine (name) values ('engine1');
insert into engine (name) values ('engine2');
insert into engine (name) values ('engine3');

insert into gear (name) values ('gear1');
insert into gear (name) values ('gear2');
insert into gear (name) values ('gear3');

insert into car (name, body_id, engine_id, gear_id) values ('car1', 1, 2, 1);
insert into car (name, body_id, engine_id, gear_id) values ('car2', 1, 3, 1);
insert into car (name, body_id, engine_id, gear_id) values ('car3', 2, 2, 1);
insert into car (name, body_id, engine_id, gear_id) values ('car4', 2, 2, 1);
insert into car (name, body_id, engine_id, gear_id) values ('car5', 1, 3, 1);

select * from car;
select * from engine;
select * from body;
select * from gear;

select c.name Авто, b.name Кузов, e.name Двигатель, g.name Коробка 
from car c
join body b on c.body_id = b.id
join engine e on e.id = c.engine_id
join gear g on g.id = c.gear_id;

select b.name Кузов, c.name Авто from body b
left join car c on c.body_id = b.id
where c.name is null;

select e.name Движок, c.name Авто from engine e
left join car c on c.engine_id = e.id
where c.name is null;

select g.name Коробка, c.name Авто from gear g
left join car c on c.gear_id = g.id
where c.name is null;