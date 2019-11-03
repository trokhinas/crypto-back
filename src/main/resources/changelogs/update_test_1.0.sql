create table role
(
	id bigserial not null
		constraint role_pkey
			primary key,
	name varchar(100)
)
;

create table "users"
(
	id bigserial not null
		constraint user_pkey
			primary key,
	name varchar(100),
	surname varchar(100),
	login varchar(100),
	password varchar(512),
	role_id integer
		constraint user_role_id_fk
			references role
)
;


create unique index user_login_uindex
	on "users" (login)
;

create table mark
(
	id bigserial not null
		constraint mark_pkey
			primary key,
	user_id integer
		constraint mark_user_id_fk
			references "users" ON DELETE CASCADE,
	test_id integer
		constraint mark_test_id_fk
			references test,
	correct_answer integer,
	all_question integer
)
;


