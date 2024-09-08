-- liquibase formatted sql

-- changeset dave:1.02 failOnError:true splitStatements:true

CREATE TABLE person (
    id VARCHAR(10) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    org VARCHAR(20),
    role VARCHAR(20),
    locale VARCHAR(10),
    level INT DEFAULT 0,
    hire_date DATE,
    email VARCHAR(255) UNIQUE,
    addr1 VARCHAR(255),
    addr2 VARCHAR(255),
    city VARCHAR(255),
    region VARCHAR(50),
    country VARCHAR(50),
    phone1 VARCHAR(20),
    phone2 VARCHAR(20)
);

CREATE TABLE org (
    id VARCHAR(10) PRIMARY KEY,
    leader_id VARCHAR(10),
    leader_date DATE DEFAULT CURRENT_DATE,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE org_members (
    org_id VARCHAR(50) not null,
    person_id VARCHAR(50) not null,
    active_flag VARCHAR(1) not null default 'Y',
    joined_at DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (org_id, person_id),
    FOREIGN KEY (org_id) REFERENCES org(id) ON DELETE CASCADE,
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);