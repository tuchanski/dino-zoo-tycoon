package application;

import controllers.UserController;
import controllers.ZooController;
import exceptions.EntityAlreadyRegisteredException;
import models.User;
import models.Zoo;
import models.enums.FoodType;
import repositories.FoodStockRepositoryImpl;
import repositories.VisitorRepositoryImpl;

public class Main {
    public static void main(String[] args) throws EntityAlreadyRegisteredException {

        // zoo

        UserController userController = new UserController();

        userController.createUser("tuchanski", "pass123");

        User u = userController.getUserByUsername("tuchanski");

        ZooController zooController = new ZooController(u);

        zooController.createZoo("Canoeiro");

        Zoo zoo = zooController.getZooById(1);

        VisitorRepositoryImpl visitorRepository = new VisitorRepositoryImpl(zoo);

        FoodStockRepositoryImpl foodStockRepository = new FoodStockRepositoryImpl(zoo);

        foodStockRepository.addFood(FoodType.MEAT, 20);

    }
}