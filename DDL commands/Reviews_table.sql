create table reviews(
Review_id int auto_increment primary key,
R_statement varchar(100),
Customer_id int not null,
foreign key(Customer_id) references customer(id),
Restuarent_id int not null,
foreign key(Restuarent_id) references restaurent(Restuarent_id),
Star_rating int not null,
Date_and_time timestamp,
Created_at timestamp not null,
Modified_at timestamp not null
);
desc reviews;
