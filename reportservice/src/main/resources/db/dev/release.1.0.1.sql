-- liquibase formatted sql

-- changeset dave:1.0.1 failOnError:true splitStatements:true

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
    phone2 VARCHAR(20),
    create_user VARCHAR not null,
    create_date timestamp not null,
    update_user VARCHAR not null,
    update_date timestamp not null
);

CREATE TRIGGER on_new_row_person BEFORE INSERT ON person FOR EACH ROW EXECUTE FUNCTION on_new_row();

CREATE TABLE org (
    id VARCHAR(10) PRIMARY KEY,
    leader_id VARCHAR(10),
    leader_date DATE DEFAULT CURRENT_DATE,
    name VARCHAR(255) NOT NULL,
    create_user VARCHAR not null,
    create_date timestamp not null,
    update_user VARCHAR not null,
    update_date timestamp not null
);

CREATE TRIGGER on_new_row_org BEFORE INSERT ON org FOR EACH ROW EXECUTE FUNCTION on_new_row();

CREATE TABLE org_members (
    org_id VARCHAR(50) not null,
    person_id VARCHAR(50) not null,
    active_flag VARCHAR(1) not null default 'Y',
    joined_at DATE DEFAULT CURRENT_DATE,
    create_user VARCHAR not null,
    create_date timestamp not null,
    update_user VARCHAR not null,
    update_date timestamp not null,
    PRIMARY KEY (org_id, person_id),
    FOREIGN KEY (org_id) REFERENCES org(id) ON DELETE CASCADE,
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

CREATE TRIGGER on_new_row_org_members BEFORE INSERT ON org_members FOR EACH ROW EXECUTE FUNCTION on_new_row();