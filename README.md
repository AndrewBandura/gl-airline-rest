## Synopsis

REST service for managing airline company. Simple version with data storage in JSON file and limited number of commands.  

#### Available commands:
<li> 
C_CAPACITY     : Calculates carrying capacity of aircraft
</li>
<li>
DISPLAY        : Displays aircraft sorted by flight range
</li>
<li>
EXIT           : Exits from command processor
</li>
<li>
FIND           : Finds aircraft by range of fuel consumption. Example: "find 100 1000 "
</li>
<li>
HELP           : Displays help info
</li>
<li>
T_CAPACITY     : Calculates total capacity of aircraft
</li> 

## Stack of technologies

Sprihg boot, JPA (Hibernate), H2 (test profile), Postgres(prod profile).
Entities with inheritance are mapped into Hibernate using strategy "One table per hierarchy with discriminator column".

Application is deployed on Heroku cloud, where Postgres DB is used.

https://gl-airline.herokuapp.com/ 


## Task

Create REST service “Airline” with CRUD operations and following services:
1. Define aircraft class hierarchy. There should be 3 levels of the hierarchy.
2. Create an airline company that stores airplanes.
3. Calculate total capacity and carrying capacity of all the aircraft in the airline.
4. Sort the aircrafts by flight range (from smaller to larger) and display it on screen.
5. Find aircraft corresponding to the specified range of fuel consumption parameters (liters per hour).
