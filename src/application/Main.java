package application;

import controllers.UserController;
import exceptions.EntityNotFoundException;
import models.User;
import repositories.ZooRepositoryImpl;

public class Main {
    public static void main(String[] args) throws EntityNotFoundException {

        UserController userController = new UserController();

        User u = userController.getUserByUsername("fortnite_da_silva");

        ZooRepositoryImpl zooRepository = new ZooRepositoryImpl(u);

        System.out.println(zooRepository.getAllZoos());

        zooRepository.createZoo("Fornite da Ilha", "Guarabira");

        System.out.println(zooRepository.getAllZoos());

        zooRepository.updateZooById(2L, "Ilha do Fornite", "Guarabira");

        System.out.println(zooRepository.getAllZoos());

    }
}