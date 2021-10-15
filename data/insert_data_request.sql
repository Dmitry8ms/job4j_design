insert into role(role_name) values('admin');
insert into users(user_name, role_id) values('Petr Arsentev', 1);
insert into state(status) values('request accepted');
insert into category(category_name) values('complaint');
insert into item(request_name, request_date, user_id, category_id, state_id)
	values(
	'notebook is broken', '2021-10-15', 1, 1, 1);