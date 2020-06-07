insert into App_User ( USER_NAME, ENCRYTED_PASSWORD, ENABLED, first_name, surname, is_first_time)
values ( 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1, null, null, 0);

insert into App_User ( USER_NAME, ENCRYTED_PASSWORD, ENABLED, first_name, surname, is_first_time)
values ('dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1, null, null, 0);

insert into app_role ( ROLE_NAME)
values ( 'ROLE_ADMIN');   

insert into app_role ( ROLE_NAME)
values ( 'ROLE_USER');

insert into user_role ( USER_ID, ROLE_ID) values ( 101, 100); 

insert into user_role ( USER_ID, ROLE_ID) values ( 101, 101);  

insert into user_role ( USER_ID, ROLE_ID) values ( 100, 101);