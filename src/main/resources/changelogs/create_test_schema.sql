create table if not exists test
(
  id bigserial not null
    constraint task_pkey
    primary key,
  title varchar(100)
)
;
CREATE TABLE public.task_type
(
    id bigserial PRIMARY KEY,
    title varchar(255),
    type varchar(100)
);


create table if not exists question
(
  id bigserial not null
    constraint question_pk
    primary key,
  name varchar(500)
)
;

create table task
(
	id bigserial not null
		constraint task_pkey1
			primary key,
	type_id integer
		constraint task_task_type_id_fk
			references task_type,
	question_id integer
		constraint task_question_id_fk
			references question,
	test_id integer
		constraint task_test_id_fk
			references test
)
;






create table if not exists answer
(
  id bigserial not null
    constraint answer_pk
    primary key,
  question_id integer
    constraint answer_question_id_fk
    references question,
  name varchar(500),
  is_correct boolean
)
;
