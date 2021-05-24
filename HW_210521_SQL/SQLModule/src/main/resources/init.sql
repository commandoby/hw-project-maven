CREATE SCHEMA IF NOT EXISTS hospital;
USE hospital;

CREATE TABLE IF NOT EXISTS medicalWorkers (
    id          INT NOT NULL,
    name        varchar(100),
    surname     varchar(100),
    position    varchar(100),
    department  varchar(100),
    years       INT
);