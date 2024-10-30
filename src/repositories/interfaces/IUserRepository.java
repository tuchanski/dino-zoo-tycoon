package repositories.interfaces;

import java.util.List;

import exceptions.UserAlreadyRegisteredException;
import exceptions.UserNotFoundException;
import models.User;

public interface IUserRepository {
    void createUser(String username, String password) throws UserAlreadyRegisteredException;
    List<User> getUsers();
    User getUserById(int id);
    User getUserByUsername(String username);
    User deleteUserById(int id) throws UserNotFoundException;
    User updateUserById(int id, String newUsername, String newPassword) throws UserNotFoundException, UserAlreadyRegisteredException;
}
