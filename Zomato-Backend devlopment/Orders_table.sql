create table orders(
Order_id varchar(20) primary key,
Payment_id varchar(20) not null,
Price varchar(10) not null,
Restuarent_id int not null,
foreign key(Restuarent_id) references restaurent(Restuarent_id),
Customer_id int not null,
foreign key(Customer_id) references customer(id),
Status varchar(20) not null,
Delivery_person_id int not null,
foreign key(Delivery_person_id) references delivery_person(Delivery_person_id),
Created_at timestamp not null,
Modified_at timestamp not null
);
desc orders;
