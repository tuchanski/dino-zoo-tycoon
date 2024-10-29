package models;

import java.util.ArrayList;
import java.util.List;

import models.exceptions.UserAlreadyRegisteredException;

public class ZooSystem {

    // Users Section

    private static List<User> users = new ArrayList<>();

    public static void createUser(User newUser) throws UserAlreadyRegisteredException{

        for (User user : users){
            if (user.getUsername().equals(newUser.getUsername())){
                throw new UserAlreadyRegisteredException("Username already registered.");
            }
        }

        users.add(newUser);
    }

    public static void getUsers(){
        
        String msg = "Registered users: ";

        if (users.isEmpty()){
            msg += "None";
        } else {
            msg += users.toString();
        }

        System.out.println(msg);

    }

    public static void main(String[] args) throws UserAlreadyRegisteredException {
        
        createUser(new User("trgui", "null"));

        getUsers();

    }
    
}
