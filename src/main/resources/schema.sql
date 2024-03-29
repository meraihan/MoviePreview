drop table if exists users;
create table users
(
   id integer not null auto_increment,
   username varchar(255) not null,
   name varchar(255) not null,
   password varchar(255) not null,
   role varchar(255) not null,
   img_loc varchar(255) not null,
   last_login_at timestamp default CURRENT_TIMESTAMP,
   primary key(id)
);
drop table if exists ratings;
create table ratings
(
   id integer not null auto_increment,
   user_id integer not null,
   imdb_id varchar(255) not null,
   rating VARCHAR(255) not null,
   is_favourite integer(5) not null default 1,
   primary key(id)
);