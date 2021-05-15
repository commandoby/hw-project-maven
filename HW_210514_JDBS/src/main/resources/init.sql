CREATE SCHEMA IF NOT EXISTS test;
USE
test;

DROP TABLE IF EXISTS students;
CREATE TABLE students
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        varchar(100),
    surname     varchar(100),
    course_name varchar(100)
);

INSERT INTO students(name, surname, course_name)
VALUES ('Andrey', 'Zaharenko', 'Java SE Core');
INSERT INTO students(name, surname, course_name)
VALUES ('Kristine', 'Stetsko', 'Java SE Core');
INSERT INTO students(name, surname, course_name)
VALUES ('Nika', 'Zarinskaya', 'Java SE Core');
INSERT INTO students(name, surname, course_name)
VALUES ('Alexey', 'Kornilov', 'Java SE Core');
INSERT INTO students(name, surname, course_name)
VALUES ('Sergey', 'Nesterionok', 'Java SE Core');