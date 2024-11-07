package application;

import controllers.UserController;
import controllers.ZooController;
import exceptions.EntityAlreadyRegisteredException;
import models.User;

public class Main {
    public static void main(String[] args) throws EntityAlreadyRegisteredException {

        // zoo

        UserController userController = new UserController();
        userController.createUser("Fifo", "Lino");

        User u = userController.getUserByUsername("Fifo");

        ZooController zooController = new ZooController(u);

        zooController.createZoo("Paraíso das Palmeiras");
        

    }
}