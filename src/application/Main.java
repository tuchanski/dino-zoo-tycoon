package application;

import java.sql.SQLException;

import controllers.UserController;
import exceptions.UserAlreadyRegisteredException;
import exceptions.UserNotFoundException;
import models.DB;

public class Main {
    public static void main(String[] args) throws UserAlreadyRegisteredException, UserNotFoundException {

        UserController userController = new UserController();

        //userController.createUser("fodase", "teste123");

        //System.out.println(userController.getUsers());

        userController.updateUserById(2, "fodase", "novasenha");

    }
}