package application;

import controllers.UserController;
import models.User;
import repositories.ZooRepositoryImpl;

public class Main {
    public static void main(String[] args) {

        UserController userController = new UserController();

        User u = userController.getUserByUsername("fortnite_da_silva");

        ZooRepositoryImpl zooRepository = new ZooRepositoryImpl(u);

        System.out.println(zooRepository.getAllZoos());


    }
}