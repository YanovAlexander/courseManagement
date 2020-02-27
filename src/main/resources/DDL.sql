CREATE SCHEMA IF NOT EXISTS public;
--CREATING DATABASE
CREATE DATABASE COURSE_MANAGEMENT WITH OWNER postgres;
ALTER SCHEMA public OWNER TO postgres;

--SCHEMA
create table course
(
    id     serial,
    title   VARCHAR(10),
    status VARCHAR(10),
    PRIMARY KEY (id)
);

create table users(
    id         serial,
    first_name VARCHAR(10),
    last_name  VARCHAR(10),
    email      VARCHAR(15),
    user_role  VARCHAR(10),
    status     VARCHAR(10),
    course_id  int,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id)
        REFERENCES course (id)
);
alter table users  owner to postgres;

alter table course owner to postgres;

create table home_work
(
    id    serial,
    title VARCHAR(15),
    text text,
    file_path VARCHAR(50),
    course_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id)
        REFERENCES course (id)
    );
alter table home_work owner to postgres;
create table solution
(
    id serial,
    text text,
    status VARCHAR(10),
    mark int,
    user_id int,
    home_work_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    FOREIGN KEY (home_work_id)
        REFERENCES home_work (id)
    );
alter table solution owner to postgres;
