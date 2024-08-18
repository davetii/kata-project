-- liquibase formatted sql

-- changeset loadreportservicedatarelease1 failOnError:true splitStatements:false



-- insert person
insert into person
(id, first_name,  last_name, role, locale, level, hire_date, email, addr1, addr2, city, region, country, phone1, phone2)
values
('abc123', 'abc123', 'SalesPerson1', 'SALES', 'en-US', 4, '2020-08-17', 'salespersonabc123@kataproject.com', 'addr1', 'addr2', 'sales1city', 'MI', 'USA', '5868732121', null);

insert into person
(id, first_name,  last_name, role, locale, level, hire_date, email, addr1, addr2, city, region, country, phone1, phone2)
values
('abc124', 'abc124',  'SalesPerson2', 'SALES', 'en-US', 5, '2020-10-07', 'salespersonabc124@kataproject.com', '123 ', 'addr2', 'sales2city', 'OH', 'USA', '5556667777', null);

insert into person
(id, first_name,  last_name, role, locale, level, hire_date, email, addr1, addr2, city, region, country, phone1, phone2)
values
('abc125', 'abc125',  'SalesPerson3', 'SALES', 'en-US', 6, '2018-01-01', 'salespersonabc125@kataproject.com', 'addr1', 'addr2', 'sales3city', 'CA', 'USA', '33337777', null);

insert into person
(id, first_name,  last_name, role, locale, level, hire_date, email, addr1, addr2, city, region, country, phone1, phone2)
values
('abc126', 'abc126',  'ServicePerson1', 'SERVICE', 'en-US', 4, '2015-01-10', 'servicepersonabc123@kataproject.com', 'addr1', null, 'service1city', 'WI', 'USA', '4446667777', null);

insert into person
(id, first_name,  last_name, role, locale, level, hire_date, email, addr1, addr2, city, region, country, phone1, phone2)
values
('abc127', 'abc127',  'ServicePerson2', 'SERVICE', 'en-US', 4, '2012-10-01', 'servicepersonabc124@kataproject.com', 'addr1', null, 'service2city', 'MI', 'USA', '3332227777', null);

-- insert orgs
insert into org (id, leader_id, name, leader_date) values ('SALES', 'abc125', 'Sales Group', '2021-01-01');
insert into org (id, leader_id, name, leader_date) values ('SERVICE', 'abc127', 'Service Group', '2012-10-01');


-- insert orgs_members
insert into org_members (org_id, person_id, active_flag, joined_at) values  ('SALES', 'abc124', 'Y', '2020-10-07');
insert into org_members (org_id, person_id, active_flag, joined_at) values  ('SALES', 'abc125', 'Y', '2020-01-01');
insert into org_members (org_id, person_id, active_flag, joined_at) values  ('SERVICE', 'abc126', 'Y', '2015-01-10');
