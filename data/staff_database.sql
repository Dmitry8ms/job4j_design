create table staff(id serial primary key, firstname varchar(50), surname varchar(50),
position text, age integer, department_id int references departments(id));
create table departments(id serial primary key, d_name varchar(50));
insert into departments(d_name) values('Logistics');
insert into departments(d_name) values('HR');
insert into departments(d_name) values('Development');
insert into staff(firstname, surname, position, age, department_id) values('Ivan', 'Ivanov', 'Junior Programmer', 21, 3);
insert into staff(firstname, surname, position, age, department_id) values('Petr', 'Petrov', 'Middle Programmer', 29, 3);
insert into staff(firstname, surname, position, age, department_id) values('Petr', 'Arsentev', 'Senior Programmer', 34, 3);
select * from departments;
create table docs(id serial primary key, doc_name varchar(100), studied varchar(100));
insert into docs(doc_name, studied) values ('Diploma 77 777', 'Computer Science');
insert into docs(doc_name, studied) values ('Job4J Certificate 11 888', 'Java');
insert into docs(doc_name, studied) values ('Skillbox Certificate 33 333', 'Java');
select * from docs;
create table staff_docs(id serial primary key, staff_id int references staff(id) unique,
 doc_id int references docs(id) unique);
insert into staff_docs(staff_id, doc_id) values (4, 3);
insert into staff_docs(staff_id, doc_id) values (5, 2);
insert into staff_docs(staff_id, doc_id) values (6, 1);
select * from staff_docs;
create table focus_groups(id serial primary key, focus_name varchar(50));
insert into focus_groups(focus_name) values('Maple app dev');
insert into focus_groups(focus_name) values('Cone app dev');
insert into focus_groups(focus_name) values('Time Machine app dev');
create table staff_focus(id serial primary key, staff_id int references staff(id),
focus_id int references focus_groups(id));
insert into staff_focus(staff_id, focus_id) values(4, 1);
insert into staff_focus(staff_id, focus_id) values(4, 2);
insert into staff_focus(staff_id, focus_id) values(4, 3);
insert into staff_focus(staff_id, focus_id) values(5, 1);
insert into staff_focus(staff_id, focus_id) values(5, 3);
insert into staff_focus(staff_id, focus_id) values(6, 2);
select * from staff_focus;