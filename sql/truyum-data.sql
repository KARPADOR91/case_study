-- insert into category
insert into category (name)
values ('Starters'), ('Main Course'), ('Dessert'), ('Drinks'); 

-- 1. a) insert into menu_item
insert into menu_item (name, price, launch_date, in_stock, category_id, free_delivery)
values ('Sandwich', 99.00, '2017-03-15', true, 2, true),
	('Burger', 129.00, '2017-12-23', true, 2, false),
    ('Pizza', 149.00, '2017-08-21', true, 2, false),
    ('French Fries', 57.00, '2017-07-02', false, 1, true),
    ('Chocholate Brownie', 32.00, '2022-11-02', true, 3, true);
    
-- 1. b) select all menu items
select * from menu_item;

-- 2. a) select all menu items after launch and active
select * from menu_items
where curdate() >= launch_date and in_stock is true;

-- 3. a) select menu item based on id
select * from menu_items
where id = 1;

-- 3. b) update menu item
update menu_item
set in_stock = false
where id = 1;

-- 4. a) insert user and cart
-- insert user
insert into user (name, admin)
values ('John', false),
	('Jane', false);
    
-- insert carts (puts Burger, French Fries and Chocolate Brownie into John's cart)
insert into cart (user_id, menu_item_id)
values (1, 2),
	(1, 4),
    (1, 5);
    
-- 5. a) select items from one user's cart
select i.name, free_delivery, price, cat.name
from menu_item i, cart c, category cat
where i.id = c.menu_item_id and
	i.category_id = cat.id and
    user_id = 1;
    
-- 5. b) total price of user's cart
select sum(price) as total
from menu_item i, cart c
where i.id = c.menu_iten_id and
	user_id = 1;
    
-- 6. a) remove item from cart
delete from cart
where user_id = 1 and menu_item_id = 5;