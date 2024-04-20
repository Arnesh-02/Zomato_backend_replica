create table delivery_person(
Delivery_person_id int auto_increment primary key,
Name varchar(20) not null,
Vechile_type varchar(20) not null,
Phone_no varchar(13) not null unique,
Status varchar (15) not null,
Address varchar (50) not null,
Working_hours varchar(20) not null,
Salary varchar(10) not null,
Created_at timestamp not null,
Modified_at timestamp not null
);
desc delivery_person;
