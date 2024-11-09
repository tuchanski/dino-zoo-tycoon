package application;

import controllers.UserController;
import controllers.ZooController;
import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.User;
import models.Zoo;
import models.enums.DinosaurSpecies;
import models.enums.FoodType;
import repositories.DinosaurRepositoryImpl;
import repositories.FoodStockRepositoryImpl;
import repositories.VisitorRepositoryImpl;

public class Main {
    public static void main(String[] args) throws EntityAlreadyRegisteredException, EntityNotFoundException {

        // zoo

        UserController userController = new UserController();

        //userController.createUser("tuchanski", "pass123");

        User u = userController.getUserByUsername("tuchanski");

        ZooController zooController = new ZooController(u);

        //zooController.createZoo("Canoeiro");

        Zoo zoo = zooController.getZooById(1);

        //DinosaurRepositoryImpl dinosaurRepository = new DinosaurRepositoryImpl(zoo);

        //dinosaurRepository.createDinosaur(DinosaurSpecies.ACROCANTHOSAURUS.toString());

        FoodStockRepositoryImpl foodStockRepository = new FoodStockRepositoryImpl(zoo);
        
        foodStockRepository.removeFood((long)2, 1000);



    }
}