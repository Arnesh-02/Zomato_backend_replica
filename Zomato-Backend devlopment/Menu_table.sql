create table menu(
Menu_id int auto_increment primary key,
Availabltiy_timt_slot varchar(25) not null,
Restuarent_id int not null,
foreign key(Restuarent_id) references restaurent(Restuarent_id),
Items_id int  not null,
foreign key(Items_id) references items(item_id),
Descrption varchar(50) not null,
Category varchar(24) not null,
Created_at timestamp not null,
Modified_at timestamp not null
);
desc menu;