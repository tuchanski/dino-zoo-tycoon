-- May be changed in the future
-- Please, create a database named DinoZooTycoon in Postgres before running this script

CREATE TABLE SystemUser (
                            user_id BIGSERIAL PRIMARY KEY,
                            username VARCHAR(50) NOT NULL,
                            password VARCHAR(50) NOT NULL,
                            cash INTEGER DEFAULT 0
);

CREATE TABLE Zoo (
                     zoo_id BIGSERIAL PRIMARY KEY,
                     name VARCHAR(100) NOT NULL,
                     user_id BIGINT REFERENCES SystemUser(user_id)
);

CREATE TABLE ParkEvent (
                           event_id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL
);

CREATE TABLE Food (
                      food_id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      type VARCHAR(20) NOT NULL
);

CREATE TABLE MeatStock (
                           stock_id BIGSERIAL PRIMARY KEY,
                           quantity INT NOT NULL,
                           zoo_id BIGINT REFERENCES Zoo(zoo_id) NOT NULL
);

CREATE TABLE EggStock (
                          stock_id BIGSERIAL PRIMARY KEY,
                          quantity INT NOT NULL,
                          zoo_id BIGINT REFERENCES Zoo(zoo_id) NOT NULL
);

CREATE TABLE PlantStock (
                            stock_id BIGSERIAL PRIMARY KEY,
                            quantity INT NOT NULL,
                            zoo_id BIGINT REFERENCES Zoo(zoo_id) NOT NULL
);

CREATE TABLE Dinosaur (
                          dinosaur_id BIGSERIAL PRIMARY KEY,
                          species VARCHAR(50) NOT NULL,
                          diet_type VARCHAR(50) NOT NULL,
                          zoo_id BIGINT REFERENCES Zoo(zoo_id) NOT NULL
);

CREATE TABLE Visitor (
                         visitor_id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         zoo_id BIGINT REFERENCES Zoo(zoo_id) NOT NULL
);

CREATE TABLE Employee (
                          employee_id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          zoo_id BIGINT REFERENCES Zoo(zoo_id) NOT NULL
);
