create database products;

create table type (
	id serial primary key, name varchar(50)
);
create table product (
	id serial primary key, 
	name varchar(100), 
	id_type int references type(id), 
	expired_date date, 
	price dec(10, 2)
);

select * from type;
select * from product;

insert into type (name) values ('Сыр');
insert into type (name) values ('Молоко');
insert into type (name) values ('Колбаса');

delete from type where id = 4;

insert into product (name, id_type, expired_date, price)
values ('Сыр плавленный', 1, '2021-10-31', 150.99);
insert into product (name, id_type, expired_date, price)
values ('Сыр плавленный', 1, '2021-10-24', 150.99);
insert into product (name, id_type, expired_date, price)
values ('Сыр моцарелла', 1, '2021-11-10', 210.99);
insert into product (name, id_type, expired_date, price)
values ('Деревенское молочко', 2, '2021-10-30', 60.99);
insert into product (name, id_type, expired_date, price)
values ('мороженое "Советское"', 2, '2021-12-31', 50.99);
insert into product (name, id_type, expired_date, price)
values ('Колбаса "Краковская"', 3, '2021-10-31', 250.99);
insert into product (name, id_type, expired_date, price)
values ('Колбаса "Московская"', 3, '2021-10-31', 350.99);
insert into product (name, id_type, expired_date, price)
values ('Сервелат "Праздничный"', 3, '2021-10-31', 450.99);

select p.name, p.expired_date, p.price from product p 
join type t 
on p.id_type = t.id
where t.name = 'Сыр';

select name, expired_date, price from product
where name ilike '%мороженое%';

select name, expired_date, price from product
where expired_date < current_date;

select name Продукт, price Цена_макс from product
where price = (select max(price) from product);

select t.name, count(p.name) from product p 
join type t
on p.id_type = t.id
group by t.name;

select t.name, count(p.name) from product p 
join type t
on p.id_type = t.id
group by t.name
having t.name in ('Молоко', 'Сыр');

select t.name, count(p.name) from product p 
join type t
on p.id_type = t.id
group by t.name
having count(p.name) < 3;

select p.name Продукт, t.name Категория, p.expired_date Срок_годности, p.price Цена
from product p 
join type t 
on p.id_type = t.id;