create table users (
  id bigint auto_increment primary key,
  email varchar(500) not null,
  name varchar(300) not null,
  password varchar(500) not null,
  role varchar(300) not null
);

create table roles (
  id varchar(300) primary key,
  description varchar(500) not null
);

create table products (
  id int auto_increment primary key,
  name varchar(500) not null,
  price numeric (15,3) not null
);

create table pickup_location (
  id integer primary key,
  location varchar(1000) not null
);

create table customers (
  id bigint auto_increment primary key,
  fullname varchar(1000) not null,
  phone_number varchar(1000),
  details varchar(4000)
);

create table orders (
  id bigint auto_increment primary key,
  duedate date not null,
  duetime time not null,
  pickup_location integer not null,
  paid boolean,
  customer bigint not null,
  state varchar(1000)
);

create table orderitems (
  sequence bigint,
  orderid bigint,
  quantity bigint not null,
  product int not null,
  comment varchar(4000),
  primary key(sequence, orderid)
);

create table order_history (
	order_id bigint not null,
	id bigint auto_increment not null,
	message varchar(1000) not null,
	timestamp timestamp not null, 
	created_by bigint not null, 
	primary key (order_id, id)
);
