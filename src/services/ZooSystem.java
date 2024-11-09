package services;

import controllers.UserController;
import controllers.ZooController;
import models.User;
import models.Zoo;

public class ZooSystem {

    private static User currentUser;
    private static Zoo currentZoo;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ZooSystem.currentUser = currentUser;
        System.out.println("Current user: " + currentUser);
    }

    public static Zoo getCurrentZoo() {
        return currentZoo;
    }

    public static void setCurrentZoo(Zoo currentZoo) {
        ZooSystem.currentZoo = currentZoo;
        System.out.println("Current zoo: " + currentZoo);
    }

}
