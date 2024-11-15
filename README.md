# Dino Zoo Tycoon 🦖🏞️💼

## Description 📝

**Dino Zoo Tycoon** is a dinosaur-themed zoo management simulator where players take on the role of a park manager in charge of a zoo filled with genetically resurrected dinosaurs. The goal is to balance the safety and well-being of the dinosaurs, the entertainment of visitors, and the overall success of the park.

**Dino Zoo Tycoon** is also considered by professor [Marina de Lara](https://github.com/akitodr) to be the best final project presented in the subject of Object-Oriented Programming at PUCPR.

## Overall Experience 🌟

Here are some screens of the game, where it is generally possible to perform CRUD operations on the system entities.

- **Main Menu**

<img src="helpers\rdmeresources\Main Menu.png" alt="Main Menu" width="600px">

- **Add Dinosaur**

<img src="helpers\rdmeresources\Add Dinosaur.png" alt="Add Dinosaur" width="600px">

- **Manage Dinosaurs**

<img src="helpers\rdmeresources\Manage Dinosaurs.png" alt="Manage Dinosaurs" width="600px">

- **Manage Employees**

<img src="helpers\rdmeresources\Manage Employees 2.png" alt="Manage Employees" width="600px">

- **Manage Visitors**

<img src="helpers\rdmeresources\Manage Visitors.png" alt="Manage Visitors" width="600px">

## How to Play 🕹️

### Setup

- Ensure Java & PostgreSQL are installed on your local machine.

- Create a database called **DinoZooTycoon**.

- Run the script.sql file in helpers/database.

- Import the [*postgresql-42.7.4.jar*](https://repo1.maven.org/maven2/org/postgresql/postgresql/42.7.4/postgresql-42.7.4.jar) library into your project structure.

### Starting the Game

- To play, just launch the application - the Main class is located in `src/application/`.

- Use the registration page to sign up – don't worry, passwords are encrypted!

- Start managing: Explore features like hiring employees, attracting visitors, and expanding your zoo.

- P.S: We’re planning to convert this application, currently built in pure Java, to Maven for better dependency management and project organization.

## Techs ⚙️

- **Java 21**
- **Java Swing for GUI**
- **PostgreSQL for Database**

## Project Structure 🧩

- `src/application/`: Contains the Main class of the program
- `src/config/`: Project configuration settings
- `src/controllers/`: Project controllers
- `src/exceptions/`: Custom exception classes
- `src/models/`: Project entities
- `src/repositories/`: Entity repositories
- `src/services/`: General services for the Zoo system
- `src/views/`: Application user interfaces

## Authors ✍️

- [Guilherme Tuchanski | @tuchanski](https://github.com/tuchanski)
- [ Luiz Matoso | @luiz-matoso](https://github.com/luiz-matoso)
