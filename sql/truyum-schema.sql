-- create menu_item table
create table menu_item (
	id int auto_increment primary key not null,
	name varchar(65) not null,
    price decimal(8,2) not null,
    launch_date date not null,
    in_stock boolean not null,
    category_id int not null,
    free_delivery boolean not null,
    foreign key (category_id) references category(id));
    
-- create category table
create table category (
	id int auto_increment primary key not null,
    name varchar(45) not null);
    
-- create user table
create table user (
	id int auto_increment primary key not null,
    name varchar(45) not null,
    admin boolean not null);
    
-- create cart table
create table cart (
	id int auto_increment primary key not null,
    user_id int not null,
    menu_item_id int not null,
    foreign key (user_id) references user(id),
    foreign key (menu_item_id) references menu_item(id));