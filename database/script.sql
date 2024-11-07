-- May be changed in the future
-- Please, create a database named DinoZooTycoon in Postgres before running this script

CREATE TYPE FoodType AS ENUM ('Meat', 'Egg', 'Plant');

CREATE TABLE SystemUser (
                            user_id BIGSERIAL PRIMARY KEY,
                            username VARCHAR(50) NOT NULL,
                            password VARCHAR(50) NOT NULL
);

CREATE TABLE Zoo (
                     zoo_id BIGSERIAL PRIMARY KEY,
                     name VARCHAR(100) NOT NULL,
                     cash INTEGER DEFAULT 0,
                     user_id BIGINT REFERENCES SystemUser(user_id)
);

CREATE TABLE ParkEvent (
                           event_id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL
);

CREATE TABLE Food (
                      food_id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      type FoodType NOT NULL
);

CREATE TABLE FoodStock (
                    zoo_id BIGINT REFERENCES Zoo(zoo_id) NOT NULL,
                    food_type FoodType NOT NULL,
                    quantity INTEGER DEFAULT 0,
                    PRIMARY KEY (zoo_id, food_type)
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
