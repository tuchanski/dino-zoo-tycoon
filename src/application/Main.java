package application;

import controllers.InventoryController;
import controllers.UserController;
import controllers.ZooController;
import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.User;
import repositories.InventoryRepositoryImpl;
import repositories.ZooRepositoryImpl;
import repositories.interfaces.IInventoryRepository;
import repositories.interfaces.IZooRepository;

public class Main {
    public static void main(String[] args) throws EntityAlreadyRegisteredException, EntityNotFoundException {

        UserController userController = new UserController();

        //userController.createUser("Teste", "1234");

        User u = userController.getUserByUsername("Teste");
        ZooController zooController = new ZooController(u);
        InventoryController inventoryController = new InventoryController();

        //zooController.createZoo("Sitio do Tio Ari", "Lapa - PR");

        //System.out.println(inventoryController.getAllInventories());

        zooController.deleteZooById(3);





    }
}