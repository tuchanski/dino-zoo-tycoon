-- Ensure a database named DinoZooTycoon is created in Postgres before running this script.

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
    type VARCHAR(10) NOT NULL CHECK (type IN ('MEAT', 'PLANT', 'EGG')),
    price INTEGER NOT NULL
);

CREATE TABLE FoodStock (
    zoo_id BIGINT REFERENCES Zoo(zoo_id) NOT NULL,
    food_id BIGINT REFERENCES Food(food_id) NOT NULL,
    quantity INTEGER DEFAULT 0,
    PRIMARY KEY (zoo_id, food_id)
);

CREATE TABLE Dinosaur (
    dinosaur_id BIGSERIAL PRIMARY KEY,
    species VARCHAR(50) NOT NULL,
    diet_type VARCHAR(10) NOT NULL CHECK (diet_type IN ('Carnivore', 'Herbivore', 'Omnivore')),
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

-- Populating Food
INSERT INTO Food (name, type, price) VALUES ('Steak', 'MEAT', 5);
INSERT INTO Food (name, type, price) VALUES ('Broccoli', 'PLANT', 5);
INSERT INTO Food (name, type, price) VALUES ('Omelet', 'EGG', 5);
