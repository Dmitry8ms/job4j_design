create table staff(id serial primary key, firstname varchar(256), surname varchar(256),
position text, age integer);
insert into staff(name, surname, position, age) values('Ivan', 'Ivanov', 'Senior assistent of junior assistent', 18);
select * from staff;
update staff set position = 'Senior assistent of junior assistent of junior programmer';
delete from staff;