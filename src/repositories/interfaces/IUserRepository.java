package repositories.interfaces;

import java.util.List;

import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.User;

public interface IUserRepository {
    void createUser(String username, String password) throws EntityAlreadyRegisteredException;
    List<User> getUsers();
    User getUserById(int id);
    User getUserByUsername(String username);
    User deleteUserById(int id) throws EntityNotFoundException;
    User updateUserById(int id, String newUsername, String newPassword) throws EntityNotFoundException, EntityAlreadyRegisteredException;
}
