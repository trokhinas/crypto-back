insert into role values (1000, 'USER');
insert into role values (2000, 'TEACHER');
insert into role values (3000, 'ADMIN');

insert into "users" (name, surname, login, password, role_id)
values ('Tosha', 'Trokhin', 'tosha', 'qwerty', 3000);
insert into "users" (name, surname, login, password, role_id)
values ('login', 'login', 'loginovich', 'qwerty', 1000);
insert into "users" (name, surname, login, password, role_id)
values ('Nikita', 'nik', 'potapov', 'qwerty', 2000);
