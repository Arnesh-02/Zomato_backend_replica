create table items(
Item_id int auto_increment primary key,
Item_name varchar(25) not null,
Descrption varchar(50) not null,
Price varchar(10) not null,
Star_rating int,
Restuarent_id int not null,
foreign key(Restuarent_id) references restaurent(Restuarent_id),
Category varchar(25) not null,
Size varchar (15),
Quantitiy int not null,
Created_at timestamp not null,
Modified_at timestamp not null
);
Desc items;