package application;

import controllers.UserController;
import models.User;
import repositories.ZooRepositoryImpl;

public class Main {
    public static void main(String[] args) {

        UserController userController = new UserController();

        System.out.println(userController.getUsers());

        User u = userController.getUserByUsername("fortnite_da_silva");

        ZooRepositoryImpl zooRepository = new ZooRepositoryImpl(u);

        zooRepository.createZoo("Floresta Bonita", "Paraná");

    }
}