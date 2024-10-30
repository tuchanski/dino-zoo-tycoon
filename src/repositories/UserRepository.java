package repositories;

import java.util.ArrayList;
import java.util.List;

import exceptions.UserAlreadyRegisteredException;
import exceptions.UserNotFoundException;
import models.User;

public class UserRepository {

    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void createUser(String username, String password) throws UserAlreadyRegisteredException {

        if (getUserByUsername(username) != null) {
            throw new UserAlreadyRegisteredException("Username already registered");
        }

        User newUser = new User(username, password);
        users.add(newUser);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public User getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public User deleteUserById(int id) throws UserNotFoundException {

        User toBeDeleted = getUserById(id);

        Boolean removed = users.removeIf(user -> user.getId() == id);

        if (removed) {
            return toBeDeleted;
        }

        return null;
    }

    private int getIndex(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user)) {
                return i;
            }

        }
        return -1;
    }

    public User updateUserById(int id, String newUsername, String newPassword) throws UserNotFoundException {

        User toBeUpdated = getUserById(id);

        if (toBeUpdated == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        int index = getIndex(toBeUpdated);

        if (!newUsername.equals(toBeUpdated.getUsername())) {
            toBeUpdated.setUsername(newUsername);
        }

        if (!newPassword.equals(toBeUpdated.getPassword())) {
            toBeUpdated.setPassword(newPassword);
        }

        users.set(index, toBeUpdated);

        return toBeUpdated;
    }

}
