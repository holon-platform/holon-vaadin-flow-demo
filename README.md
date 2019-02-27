# Holon platform Vaadin Flow demo application: Bakery online store

_This is a demo project built with the [Holon Platform](https://holon-platform.com)._

## Description

This is a full stack web application that simulates the online order management of a bakery. Starting from product and user records, it is possible to manage daily orders and to analyze sales data.

The aim of the project is to show how to create a full stack application using __Vaadin Flow__ in combination with the __Vaadin Flow module of the Holon Platform__.

## Features
These are the main features of the Bakery web application:
1. *Backoffice function* to insert and manage users
2. *Backoffice function* to insert and manage bakery products
3. *Storefront function* to manage the whole lifecycle of the orders
4. *Analysis function* on sales data through dynamic graphs

## Full stack architecture

* __Spring Boot__ for packaging, configuring and running

* __H2 in memory Database__ with minimal configuration

* __Holon Platform__ with the following modules: 
  1. __JDBC__ module
  2. __Vaadin Flow__ module
  3. __Artisan__ module

* __Vaadin Addons:__
  1. [Charts](https://vaadin.com/components/vaadin-charts)
  2. [Board](https://vaadin.com/components/vaadin-board)

## Topics

This example addresses the following topics:

* Setup a web application using the Holon Platform __Vaadin Flow__ integration and __Spring Boot__ auto-configuration support.
* Use `Realm` to manage authentication/authorization aspects
* Use the `Navigator` API to handle application _routing_ and the `@Route` annotation to define routing targets. 
* Use the Holon Platform `Property` model and `Datastore` API with the Vaadin Flow components to display and manage application entities.

## Data and routing

A __JDBC__ `Datastore` backed by a __H2 database__ is used for product data persistence. All tables listed defined in `schema.sql` file are created at startup and populated using the `data.sql` file.

The Bakery application is composed by these _routes_:

* [Login](src/main/java/com/holonplatform/vaadin/flow/demo/pages/Login.java): the first page to enter credentials
* [Storefront](src/main/java/com/holonplatform/vaadin/flow/demo/pages/Storefront.java): the home page, where all the orders are listed and can be modified 
* [Products](src/main/java/com/holonplatform/vaadin/flow/demo/pages/Products.java): page to view all products at the store, insert new ones and modify the present ones
* [Users](src/main/java/com/holonplatform/vaadin/flow/demo/pages/Users.java): page to view current users, insert new ones and modify the present ones
* [Dashboard](src/main/java/com/holonplatform/vaadin/flow/demo/pages/Dashboard.java): page to analize order data through charts

## Run the demo

The Bakery demo is configured using __Spring Boot__. So from command prompt run 

`mvn spring-boot:run`

or run the `Application` class.

After application startup navigate to:

`http://localhost:8080`

It will be prompted username and password to login. Standard users are:
* admin@holon-platform.com / admin
* barista@holon-platform.com / barista

Note that the demo will be rendered correctly using FireFox. Chrome still has problems rendering Vaadin Grids.

## Documentation

The complete _Holon Platform reference guide_ is available [here](https://docs.holon-platform.com/current/reference).

For the specific documentation about the modules and the components used in this example see:

* Documentation about the [Datastore](https://docs.holon-platform.com/current/reference/holon-core.html#Datastore)  API
* Documentation about the [Realm](https://docs.holon-platform.com/current/reference/holon-core.html#Realm)  API
* Documentation about the [Vaadin Flow module](https://docs.holon-platform.com/current/reference/holon-vaadin-flow.html)

## System requirements

The Holon Platform is built using __Java 8__, so you need a JRE/JDK version 8 or above to build and run this demo project.

## License

All the [Holon Platform](https://holon-platform.com) modules and examples are _Open Source_ software released under the [Apache 2.0 license](LICENSE.md).

## Holon Platform Examples

See [Holon Platform Vaadin Flow Demo](https://github.com/holon-platform/holon-vaadin-flow-demo) for the demo web application directory.
