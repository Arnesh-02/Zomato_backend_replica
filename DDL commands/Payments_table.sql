create table payment(
Payment_id varchar(20) primary key,
Order_id varchar(20) not null unique,
foreign key (Order_id) references orders(Order_id),
Payment_method varchar(15) not null,
Price varchar(10) not null,
Date_and_time timestamp not null,
Created_at timestamp not null,
Modified_at timestamp not null
);
Desc payment;