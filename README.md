Logistics test project by Pavel Lezin
----
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

### The task is:

**1.** Build a web service for the logistics department. Information about drivers and vehicles should be kept. More than three vehicles cannot be assigned to one driver.
Vehicles can be of various types: cars, trucks, buses. Drivers can drive vehicles of categories B, C, D; driver's licenses have a validity period.

* Support CRUD operations for drivers and vehicles.
* It is necessary to develop methods of attaching a car to a driver and detaching from him, a method for displaying a list of drivers and cars assigned to them.
* When assigning a car to a driver, check the open categories, the expiration date of the driver's license and the number of cars assigned to the driver.

Write documentation for a web service using Swagger. Write a database migration script (SQL script or special tools like Liquibase or Flyway). Cover the code with unit tests using Spring context, Mockito, in-memory database.

Deploy the application to a publicly available VPS, if available, or prepare everything necessary for this. Deploy description in readme.md. In the case of deploying an application in a container environment, make a Dockerfile, include in the readme.md build and run scripts. It is desirable, but not required: the Docker-compose file for deploying everything is needed for the application to work with one command. Instead of Docker and compose, K8S and yaml manifest are welcome.

**2.** Write documentation for a web service using Swagger. Write a database migration script (SQL script or special tools like Liquibase or Flyway). Cover the code with unit tests using Spring context, Mockito, in-memory database.

**3.** Deploy the application to a publicly available VPS, if available, or prepare everything necessary for this. Deploy description in readme.md. In the case of deploying an application in a container environment, make a Dockerfile, include in the readme.md build and run scripts. It is desirable, but not required: the Docker-compose file for deploying everything is needed for the application to work with one command. Instead of Docker and compose, K8S and yaml manifest are welcome.

**4.** Let's say that our company has become a transnational organization, and half of the world's population works in it. The logistics department has grown to a billion people working all over the planet, and our application is constantly in use and cannot handle the high workload. Provide an architectural solution to handle any workload. You can use both vertical and horizontal scaling. Describe the solution in the form of diagrams, text and a stack of applied technologies.

As a result, provide a link to github repository.

It should contain the code and README.md with API documentation and curl commands for based use-cases to test it.

-----------------------------
P.S.: Make sure everything works with latest version that is on github :)

-----------------------------

**View model:**