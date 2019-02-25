-- users
insert into users (id, email, name, password, role) VALUES (1, 'admin@holon-platform.com', 'Steve', 'x61Ey612Kl2gpFL56FT9weDnpSo4AV8j8+qx2AuTHdRyY036xxzTTrw10Wq3+4qQyB+XURPWx1ONxp3Y3pB37A==', 'admin');
insert into users (id, email, name, password, role) VALUES (2, 'baker@holon-platform.com', 'Bill', 'rqOqT04K8MaDM8l29pAqQr1LeP66H4BvNdsx+NVQsOvg3LX+MAcQl92n3MADWI2i0YhMIMQqeKK3bM8WIkWdnA==', 'baker');
insert into users (id, email, name, password, role) VALUES (3, 'barista@holon-platform.com', 'John', 'yAKqAyxiaoAkdtTCIJAI8McA7E2GLSwYlOw6NjaGMOT5FbCuCcKEpsAVyGmsJEwHvBKXLG0//O2Rr5yczR4aqg==', 'barista');

-- roles
insert into roles (id, description) VALUES ('admin', 'Administrator');
insert into roles (id, description) VALUES ('baker', 'Baker');
insert into roles (id, description) VALUES ('barista', 'Barista');

-- products
insert into products (name, price) VALUES ('Blueberry Cheese Cake', 31.40);
insert into products (name, price) VALUES ('Blueberry Cookie', 87.22);
insert into products (name, price) VALUES ('Raspberry Blueberry Biscuit', 29.50);
insert into products (name, price) VALUES ('Raspberry Blueberry Tart', 79.05);
insert into products (name, price) VALUES ('Strawberry Bun', 42.74);
insert into products (name, price) VALUES ('Strawberry Cheese Cake', 78.16);
insert into products (name, price) VALUES ('Strawberry Tart', 71.49);
insert into products (name, price) VALUES ('Vanilla Blueberry Cake', 50.70);
insert into products (name, price) VALUES ('Vanilla Cracker', 98.77);
insert into products (name, price) VALUES ('Vanilla Pastry', 17.95);

-- pickup locations
insert into pickup_location (id, location) values (1, 'Store');
insert into pickup_location (id, location) values (2, 'Bakery');

--customers
insert into customers (fullname, phone_number, details) VALUES ('Jason Pickett', '00235685944', 'No details');
insert into customers (fullname, phone_number, details) VALUES ('Kerry Blevins', '00395645554', 'No details');

-- orders

-- jan 2019 orders
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-01', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-02', '19:00:00', 1, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-02', '17:00:00', 2, false, 1, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-03', '19:00:00', 1, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-04', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-05', '19:00:00', 1, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-06', '19:00:00', 1, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-06', '14:00:00', 2, true, 1, 'PROBLEM');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-07', '14:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-07', '13:00:00', 1, false, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-08', '14:00:00', 2, true, 1, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-09', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-09', '19:00:00', 2, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-09', '19:00:00', 1, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-13', '11:00:00', 1, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-13', '11:00:00', 1, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-23', '13:00:00', 1, false, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-25', '14:00:00', 1, true, 1, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-26', '13:00:00', 1, false, 2, 'CONFIRMED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-27', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-29', '19:00:00', 2, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-29', '13:00:00', 1, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-29', '19:00:00', 1, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-29', '19:00:00', 2, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-31', '17:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-31', '19:00:00', 2, true, 1, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-01-31', '19:00:00', 1, true, 2, 'NEW');

-- feb 2019 orders

insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-01', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-02', '19:00:00', 1, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-02', '17:00:00', 2, false, 1, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-03', '19:00:00', 1, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-04', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-05', '19:00:00', 1, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-06', '19:00:00', 1, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-07', '13:00:00', 1, false, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-08', '14:00:00', 1, true, 1, 'CONFIRMED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-09', '13:00:00', 1, false, 2, 'CONFIRMED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-10', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-11', '19:00:00', 2, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-12', '17:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-13', '19:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-14', '14:00:00', 2, true, 1, 'PROBLEM');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-15', '14:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-15', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-15', '19:00:00', 2, true, 2, 'PROBLEM');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-15', '17:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-16', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-16', '14:00:00', 2, true, 1, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-16', '19:00:00', 2, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-16', '19:00:00', 2, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-16', '13:00:00', 1, false, 2, 'CONFIRMED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-16', '14:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-17', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-17', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-17', '19:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-18', '19:00:00', 2, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-18', '19:00:00', 2, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-18', '14:00:00', 2, true, 1, 'PROBLEM');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-18', '14:00:00', 1, true, 1, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-19', '19:00:00', 2, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-19', '19:00:00', 2, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-19', '13:00:00', 1, false, 2, 'CONFIRMED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-19', '14:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-20', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-21', '19:00:00', 2, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-22', '17:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-23', '19:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-24', '14:00:00', 2, true, 1, 'PROBLEM');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-25', '14:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-25', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-25', '19:00:00', 2, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-25', '17:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-26', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-26', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-27', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-27', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-27', '19:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-28', '19:00:00', 2, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-28', '19:00:00', 2, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-28', '14:00:00', 2, true, 1, 'PROBLEM');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-02-28', '14:00:00', 1, true, 1, 'NEW');

-- mar 2019 orders
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-08', '14:00:00', 1, true, 1, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-09', '13:00:00', 1, false, 2, 'CONFIRMED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-10', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-11', '19:00:00', 2, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-12', '17:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-13', '19:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-14', '14:00:00', 2, true, 1, 'PROBLEM');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-15', '14:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-16', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-17', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-18', '19:00:00', 2, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-19', '19:00:00', 2, true, 2, 'DELIVERED');

-- march orders
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-13', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-09', '19:00:00', 1, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-29', '19:00:00', 1, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-13', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-29', '19:00:00', 1, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-31', '19:00:00', 1, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-23', '13:00:00', 1, false, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-25', '14:00:00', 1, true, 1, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-26', '13:00:00', 1, false, 2, 'CONFIRMED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-27', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-29', '19:00:00', 2, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-31', '17:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-31', '19:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-06', '14:00:00', 2, true, 1, 'PROBLEM');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-07', '14:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-08', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-09', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-09', '19:00:00', 2, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-03-29', '19:00:00', 2, true, 2, 'NEW');

-- august orders
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-13', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-09', '19:00:00', 1, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-29', '19:00:00', 1, true, 2, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-13', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-29', '19:00:00', 1, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-31', '19:00:00', 1, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-23', '13:00:00', 1, false, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-25', '14:00:00', 1, true, 1, 'NEW');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-26', '13:00:00', 1, false, 2, 'CONFIRMED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-27', '17:00:00', 1, true, 2, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-29', '19:00:00', 2, true, 2, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-31', '17:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-31', '19:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-06', '14:00:00', 2, true, 1, 'PROBLEM');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-07', '14:00:00', 2, true, 1, 'READY');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-08', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-09', '14:00:00', 2, true, 1, 'CANCELLED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-09', '19:00:00', 2, true, 2, 'DELIVERED');
insert into orders (duedate, duetime, pickup_location, paid, customer, state) VALUES ('2019-08-29', '19:00:00', 2, true, 2, 'NEW');

-- order items

--JAN
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 1, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 1, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 1, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 1, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 2, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 2, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 2, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 2, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 3, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 3, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 3, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 3, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 4, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 4, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 4, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 4, 4, 1, 'No comment');


insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 5, 2, 6, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 5, 3, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 5, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 5, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 5, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 5, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (7, 5, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (8, 5, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 6, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 6, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 6, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 6, 4, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 6, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 6, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 7, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 7, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 7, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 7, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 8, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 8, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 8, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 8, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 9, 2, 6, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 9, 3, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 9, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 9, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 9, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 9, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (7, 9, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (8, 9, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 10, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 10, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 10, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 10, 4, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 10, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 10, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 11, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 11, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 11, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 11, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 12, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 12, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 12, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 12, 4, 3, 'No comment');


-- FEB
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 28, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 28, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 28, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 28, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 29, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 29, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 29, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 29, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 30, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 30, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 30, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 30, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 31, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 31, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 31, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 31, 4, 1, 'No comment');


insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 32, 2, 6, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 32, 3, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 32, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 32, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 32, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 32, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (7, 32, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (8, 32, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 33, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 33, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 33, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 33, 4, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 33, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 33, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 34, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 34, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 34, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 34, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 35, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 35, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 35, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 35, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 36, 2, 6, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 36, 3, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 36, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 36, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 36, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 36, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (7, 36, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (8, 36, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 37, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 37, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 37, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 37, 4, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 37, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 37, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 38, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 38, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 38, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 38, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 39, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 39, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 39, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 39, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 43, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 43, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 43, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 43, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 44, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 44, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 44, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 44, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 45, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 45, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 45, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 45, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 46, 2, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 46, 3, 4, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 46, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 46, 4, 1, 'No comment');


insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 47, 2, 6, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 47, 3, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 47, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 47, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 47, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 47, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (7, 47, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (8, 47, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 48, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 48, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 48, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 48, 4, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 48, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 48, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 49, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 49, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 49, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 49, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 50, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 50, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 50, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 50, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 51, 2, 6, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 51, 3, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 51, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 51, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 51, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 51, 4, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (7, 51, 7, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (8, 51, 4, 1, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 52, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 52, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 52, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 52, 4, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (5, 52, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (6, 52, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 53, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 53, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 53, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 53, 4, 3, 'No comment');

insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (1, 54, 2, 1, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (2, 54, 3, 2, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (3, 54, 7, 3, 'No comment');
insert into orderitems (sequence, orderid, quantity, product, comment) VALUES (4, 54, 4, 3, 'No comment');

-- order history
insert into order_history (order_id, message, timestamp, created_by) values (18, 'Order confirmed', TIMESTAMP '2018-10-01 15:30:00', 1);
insert into order_history (order_id, message, timestamp, created_by) values (18, 'Order placed', TIMESTAMP '2018-10-01 16:30:00', 1);
insert into order_history (order_id, message, timestamp, created_by) values (18, 'Order ready for pickup', TIMESTAMP '2017-10-01 17:00:00', 2);
insert into order_history (order_id, message, timestamp, created_by) values (18, 'Order delivered', TIMESTAMP '2017-10-01 17:00:00', 2);
insert into order_history (order_id, message, timestamp, created_by) values (19, 'Order confirmed', TIMESTAMP '2017-10-02 15:30:00', 1);
insert into order_history (order_id, message, timestamp, created_by) values (19, 'Order placed', TIMESTAMP '2017-10-02 16:30:00', 1);
insert into order_history (order_id, message, timestamp, created_by) values (19, 'Order ready for pickup', TIMESTAMP '2017-10-02 17:00:00', 1);
insert into order_history (order_id, message, timestamp, created_by) values (19, 'Order delivered', TIMESTAMP '2017-10-02 17:00:00', 2);