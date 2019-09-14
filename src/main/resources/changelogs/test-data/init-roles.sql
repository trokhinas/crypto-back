insert into role values (1000, 'USER');
insert into role values (2000, 'TEACHER');
insert into role values (3000, 'ADMIN');

insert into "user" (name, surname, login, password, role_id)
values ('Tosha', 'Trokhin', 'tosha', 'qwerty', 3000);
