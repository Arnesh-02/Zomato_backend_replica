create table restaurent (
Restuarent_id int auto_increment primary key,
Name varchar(25) not null,
Star_rating int not null,
Address varchar(50) not null,
Cusines varchar(50) not null,
Phone_no varchar(13) not null unique,
Opening_hours varchar(20) not null,
Avg_delivery_time varchar(8) not null,
Avg_distance_in_kms float(4) not null,
Menu_id int not null unique,
Category varchar(20) not null,
Reviews_id int unique,
Created_at timestamp not null,
Modified_at timestamp not null
);
desc restaurent;