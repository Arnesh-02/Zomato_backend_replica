create table customer
(Id int auto_increment primary key,
Name varchar(20) not null,
Username varchar(20) not null unique,
password varchar(12) not null,
Dob varchar(10) not null,
Email varchar(25) unique,
Phone_no varchar(13) not null unique,
Gender varchar(18),
Address varchar(50) not null,
Category varchar(7) not null,
Created_at timestamp not null,
Modified_at timestamp not null
);
desc customer;

