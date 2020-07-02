drop table if exists event_cause;
create table event_cause
( cause_code int,
event_id varchar(20),
description varchar(255), 
primary key (event_id, cause_code)
);


drop table if exists failure_class;
create table failure_class
(id int not null auto_increment, 
failure_class varchar(20),
description varchar (255),
primary key (id)
);


drop table if exists user_equipment;
create table user_equipment
(tac int,
marketing_name varchar(255),
manufacturer varchar(255),
access_capability varchar(255),
model varchar (255),
vendor_name varchar (255),
ue_type varchar (40),
os varchar(40),
input_mode varchar(40),
primary key (tac)
);

drop table if exists mcc_mnc;
create table mcc_mnc
( mcc int,
mnc int,
country varchar (100),
operator varchar (255),
primary key (mcc, mnc)
);

drop table if exists users;
create table users
( user_id int auto_increment,
user_name varchar (40),
user_password varchar(40),
user_type int,
primary key (user_id)
);

drop table if exists base_data;
create table base_data
(
id int NOT NULL AUTO_INCREMENT,
date_time datetime,
event_id varchar(20),
failure_class varchar (20),
ue_type int,
market int,
operator int,
cell_id int,
duration int,
cause_code varchar (20),
ne_version varchar (20),
imsi varchar (20),
hier3_id varchar (20),
hier32_id varchar (20),
hier321_id varchar (20),
primary key (id)
);

drop table if exists invalidevent;
create table invalidevent
(
id int NOT NULL AUTO_INCREMENT,
date_time datetime,
event_id varchar(20),
failure_class varchar (20),
ue_type int,
market int,
operator int,
cell_id int,
duration int,
cause_code varchar (20),
ne_version varchar (20),
imsi varchar (20),
hier3_id varchar (20),
hier32_id varchar (20),
hier321_id varchar (20),
primary key (id)
);