## Synopsis

REST service for managing airline company.

#### Available API:

https://gl-airline.herokuapp.com/api 


## Stack of technologies

Sprihg boot, JPA (Hibernate), H2 (test profile), Postgres(prod profile).
Entities with inheritance are mapped into Hibernate using strategy "One table per hierarchy with discriminator column".
No security.

Application is deployed on Heroku cloud, where Postgres DB is used.

https://gl-airline.herokuapp.com/


If you build and run project locally on your own, apllication is available at the following URL:

http://localhost:8080


## Task

Create REST service “Airline” with CRUD operations and following services:
1. Define aircraft class hierarchy. There should be 3 levels of the hierarchy.
2. Create an airline company that stores airplanes.
3. Calculate total capacity and carrying capacity of all the aircraft in the airline.
4. Sort the aircrafts by flight range (from smaller to larger) and display it on screen.
5. Find aircraft corresponding to the specified range of fuel consumption parameters (liters per hour).
