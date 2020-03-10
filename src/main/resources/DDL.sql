CREATE SCHEMA IF NOT EXISTS public;
--CREATING DATABASE
CREATE DATABASE COURSE_MANAGEMENT WITH OWNER postgres;
ALTER SCHEMA public OWNER TO postgres;

--SCHEMA
create table course
(
    id     serial,
    title  VARCHAR(10),
    status VARCHAR(10),
    PRIMARY KEY (id)
);

create table users
(
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
alter table users
    owner to postgres;

alter table course
    owner to postgres;

create table home_work
(
    id        serial,
    title     VARCHAR(15),
    text      text,
    file_path VARCHAR(50),
    course_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id)
        REFERENCES course (id)
);
alter table home_work
    owner to postgres;

create table solution
(
    id           serial,
    text         text,
    status       VARCHAR(10),
    mark         int,
    user_id      int,
    home_work_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    FOREIGN KEY (home_work_id)
        REFERENCES home_work (id)
);
alter table solution
    owner to postgres;

ALTER TABLE course
    ALTER COLUMN title TYPE VARCHAR(50),
    ALTER COLUMN status TYPE VARCHAR(50);

ALTER TABLE users
    ALTER COLUMN email TYPE VARCHAR(50),
    ALTER COLUMN status TYPE VARCHAR(50),
    ALTER COLUMN first_name TYPE VARCHAR(50),
    ALTER COLUMN last_name TYPE VARCHAR(50);


ALTER TABLE solution
    ALTER COLUMN status TYPE VARCHAR(50);

ALTER TABLE home_work
    ALTER COLUMN title TYPE VARCHAR(50),
    ALTER COLUMN file_path TYPE VARCHAR(100);

ALTER TABLE course
    ADD UNIQUE (title);

ALTER TABLE users
    ADD UNIQUE (email);