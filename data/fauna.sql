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