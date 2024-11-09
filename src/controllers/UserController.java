package controllers;

import java.util.List;

import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.User;
import repositories.UserRepositoryImpl;
import repositories.interfaces.IUserRepository;

public class UserController {
    
    private IUserRepository userRepository = new UserRepositoryImpl();

    public void createUser(String username, String password) {

        try {
            userRepository.createUser(username, password);
        } catch (EntityAlreadyRegisteredException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public User deleteUserById(int id) {

        User user = userRepository.getUserById(id);

        try {
            userRepository.deleteUserById(id);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user;

    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User updateUserById(int id, String newUsername, String newPassword) {

        User user = null;

        try {
            userRepository.updateUserById(id, newUsername, newPassword);
            user = userRepository.getUserById(id);

        } catch (EntityNotFoundException | EntityAlreadyRegisteredException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user;
        
    }
}
