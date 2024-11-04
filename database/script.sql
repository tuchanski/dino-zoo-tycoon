-- May be changed in the future

CREATE TABLE SystemUser (
                            user_id BIGSERIAL PRIMARY KEY,
                            username VARCHAR(50) NOT NULL,
                            password VARCHAR(50) NOT NULL
);

CREATE TABLE Zoo (
                     zoo_id BIGSERIAL PRIMARY KEY,
                     name VARCHAR(100) NOT NULL,
                     location VARCHAR(100) NOT NULL,
                     user_id BIGINT REFERENCES SystemUser(user_id)
);

CREATE TABLE ParkEvent (
                           event_id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           date DATE NOT NULL,
                           zoo_id BIGINT REFERENCES Zoo(zoo_id)
);

CREATE TABLE Inventory (
                           inventory_id BIGSERIAL PRIMARY KEY,
                           zoo_id BIGINT REFERENCES Zoo(zoo_id)
);

CREATE TABLE Food (
                      food_id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      quantity INT NOT NULL,
                      inventory_id BIGINT REFERENCES Inventory(inventory_id)
);

CREATE TABLE Enclosure (
                           enclosure_id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           size INT NOT NULL,
                           inventory_id BIGINT REFERENCES Inventory(inventory_id)
);

CREATE TABLE Dinosaur (
                          dinosaur_id BIGSERIAL PRIMARY KEY,
                          species VARCHAR(50) NOT NULL,
                          diet_type VARCHAR(50) NOT NULL,
                          enclosure_id BIGINT REFERENCES Enclosure(enclosure_id)
);

CREATE TABLE Visitor (
                         visitor_id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         visit_date DATE NOT NULL,
                         inventory_id BIGINT REFERENCES Inventory(inventory_id)
);

CREATE TABLE Employee (
                          employee_id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          job_title VARCHAR(100) NOT NULL,
                          salary DECIMAL(10, 2) NOT NULL,
                          inventory_id BIGINT REFERENCES Inventory(inventory_id)
);

CREATE TABLE Ticket (
                        ticket_id BIGSERIAL PRIMARY KEY,
                        price DECIMAL(5, 2) NOT NULL,
                        visitor_id BIGINT REFERENCES Visitor(visitor_id)
);
