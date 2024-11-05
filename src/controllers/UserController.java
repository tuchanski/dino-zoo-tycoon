package controllers;

import java.util.List;

import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.User;
import repositories.UserRepositoryImpl;
import repositories.interfaces.IUserRepository;

public class UserController {
    
    private IUserRepository userRepository = new UserRepositoryImpl();

    public void createUser(String username, String password) throws EntityAlreadyRegisteredException {
        userRepository.createUser(username, password);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public User deleteUserById(int id) throws EntityNotFoundException {
        return userRepository.deleteUserById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User updateUserById(int id, String newUsername, String newPassword) throws EntityNotFoundException, EntityAlreadyRegisteredException {
        return userRepository.updateUserById(id, newUsername, newPassword);
    }

}
