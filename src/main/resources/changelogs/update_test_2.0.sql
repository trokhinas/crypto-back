create table course
(
	id bigserial not null
		constraint course_pkey
			primary key,
	name varchar(200)
)
;

create table course_themes
(
	id bigserial not null
		constraint course_themes_pkey
			primary key,
	name varchar(200),
	reference varchar(200),
	course_id integer
		constraint course_themes_course_id_fk
			references course,
	user_id integer
		constraint course_themes_users_id_fk
			references users
)
;


create table subscribed_courses
(
	id bigserial not null
		constraint subscribed_courses_pkey
			primary key,
	user_id integer
		constraint subscribed_courses_users_id_fk
			references users,
	course_id integer
		constraint subscribed_courses_course_id_fk
			references course
)
;


