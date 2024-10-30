package controllers;

import java.util.List;

import exceptions.UserAlreadyRegisteredException;
import exceptions.UserNotFoundException;
import models.User;
import repositories.UserRepositoryImpl;
import repositories.interfaces.IUserRepository;

public class UserController {
    
    private IUserRepository userRepository = new UserRepositoryImpl();

    public void createUser(String username, String password) throws UserAlreadyRegisteredException {
        userRepository.createUser(username, password);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public User deleteUserById(int id) throws UserNotFoundException {
        return userRepository.deleteUserById(id);
    }

    public User updateUserById(int id, String newUsername, String newPassword) throws UserNotFoundException, UserAlreadyRegisteredException {
        return userRepository.updateUserById(id, newUsername, newPassword);
    }

}
