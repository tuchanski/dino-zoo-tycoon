package services;

import controllers.UserController;
import controllers.ZooController;
import models.User;
import models.Zoo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static String hashPassword(String password) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

            StringBuilder hexStringPass = new StringBuilder();

            for (byte b : messageDigest) {
                hexStringPass.append(String.format("%02X", 0xFF & b));
            }

            return hexStringPass.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

}
