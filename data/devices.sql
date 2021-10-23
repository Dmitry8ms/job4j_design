create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('Lenovo Tab 10\"', 19500.5);
insert into devices (name, price) values ('iPhone 13', 99999.9);
insert into devices (name, price) values ('Yandex станция', 12900);
insert into devices (name, price) values ('iPhone 13', 149999.9);

select * from devices order by id;

update devices set name = 'Lenovo Tab 10"' where id = 1;

insert into people (name) values ('Ivan Ivanov');
insert into people (name) values ('Vasiliy Vasiliev');
insert into people (name) values ('Petr Arsentev');

select * from people;

insert into devices_people (people_id, device_id) values (1, 1);
insert into devices_people (people_id, device_id) values (1, 4);
insert into devices_people (people_id, device_id) values (2, 1);
insert into devices_people (people_id, device_id) values (2, 2);
insert into devices_people (people_id, device_id) values (3, 1);
insert into devices_people (people_id, device_id) values (3, 2);
insert into devices_people (people_id, device_id) values (3, 3);

select p.name Владелец, d.name Устройство, d.price from people p join devices_people d_p 
on p.id = d_p.people_id
join devices d
on d.id = d_p.device_id
order by p.name;

select p.name Владелец, cast(avg(d.price) as dec(8,2)) Средняя_цена_руб from people p join devices_people d_p 
on p.id = d_p.people_id
join devices d
on d.id = d_p.device_id
group by p.name;

select d.name Устройство, cast(avg(d.price) as dec(8,2)) Средняя_цена_руб from people p join devices_people d_p 
on p.id = d_p.people_id
join devices d
on d.id = d_p.device_id
group by d.name;

select p.name Владелец, cast(avg(d.price) as dec(8,2)) Средняя_цена_руб from people p join devices_people d_p 
on p.id = d_p.people_id
join devices d
on d.id = d_p.device_id
group by p.name
having avg(d.price) > 50000;