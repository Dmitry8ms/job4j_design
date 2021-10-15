create table role(
	id serial primary key, role_name varchar(50));
create table users(
	id serial primary key, user_name varchar(50), role_id int
	references role(id));
create table rules(
	id serial primary key, rule_name varchar(50));
create table role_rules(
	id serial primary key, role_id int references role(id),
	rules_id int references rules(id));
create table category(
	id serial primary key, category_name varchar(50));
create table state(
	id serial primary key, status varchar(20));
create table item(
	id serial primary key, request_name varchar(100),
	request_date date, user_id int references users(id),
	category_id int references category(id), state_id int
	references state(id));
create table comments(
	id serial primary key, comment text, 
	user_id int references item(id));
create table attachs(
	id serial primary key, attachment inet,
	user_id int references item(id));