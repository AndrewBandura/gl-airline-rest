INSERT INTO company (id,name ) VALUES (1,'RyanAir');
INSERT INTO company (id,name) VALUES (2,'British Airways');

 INSERT INTO aircraft (id, type, name, flight_range, total_capacity, carrying_capacity, fuel_consumption, engine_num, unpaved, company_id)
 VALUES (1, 'TurboPropPlane', 'AN-22', 999, 55, 50, 300.7, 2, true, 1);

INSERT INTO aircraft (id, type, name, flight_range, total_capacity, carrying_capacity, fuel_consumption, engine_num, company_id)
 VALUES (2, 'JetPlane', 'Boeing-737', 4000, 155, 150, 1000, 2, 1);


INSERT INTO aircraft (id, type, name, flight_range, total_capacity, carrying_capacity, fuel_consumption, engine_num, company_id)
 VALUES (3, 'JetPlane', 'Airbus-320', 2000, 130, 125, 700.2, 2, 2);

INSERT INTO aircraft (id, type, name, flight_range, total_capacity, carrying_capacity, fuel_consumption, reg_num, company_id)
 VALUES (4, 'Helicopter', 'Mi-8', 500, 10, 8, 100.2, 'HE234567534', 2);