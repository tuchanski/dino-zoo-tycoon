package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.DB;
import models.User;
import repositories.interfaces.IUserRepository;
import services.ZooSystem;

public class UserRepositoryImpl implements IUserRepository {

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    @Override
    public void createUser(String username, String password) throws EntityAlreadyRegisteredException {

        if (!usernameIsAvailable(username)) {
            throw new EntityAlreadyRegisteredException("Username already registered");
        }

        String hashedPassword = ZooSystem.hashPassword(password);
        String createUserQuery = "INSERT INTO SystemUser (username, password) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement createUserPs = conn.prepareStatement(createUserQuery)) {

            createUserPs.setString(1, username);
            createUserPs.setString(2, hashedPassword);
            createUserPs.execute();
            System.out.println("User " + username + " has been created successfully");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> registeredUsers = new ArrayList<>();
        String getUsersQuery = "SELECT * FROM SystemUser";

        try (Connection conn = getConnection();
             PreparedStatement getUsersPs = conn.prepareStatement(getUsersQuery);
             ResultSet rs = getUsersPs.executeQuery()) {

            while (rs.next()) {
                Long userId = rs.getLong("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                registeredUsers.add(new User(userId, username, password));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return registeredUsers;
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        String getUserByIdQuery = "SELECT * FROM SystemUser WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getUserByIdPs = conn.prepareStatement(getUserByIdQuery)) {

            getUserByIdPs.setInt(1, id);
            try (ResultSet rs = getUserByIdPs.executeQuery()) {
                if (rs.next()) {
                    Long userId = rs.getLong("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    user = new User(userId, username, password);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        String getUserByUsernameQuery = "SELECT * FROM SystemUser WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement getUserByUsernamePs = conn.prepareStatement(getUserByUsernameQuery)) {

            getUserByUsernamePs.setString(1, username);
            try (ResultSet rs = getUserByUsernamePs.executeQuery()) {
                if (rs.next()) {
                    Long userId = rs.getLong("user_id");
                    String password = rs.getString("password");
                    user = new User(userId, username, password);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return user;
    }

    @Override
    public User deleteUserById(int id) throws EntityNotFoundException {
        User userToBeDeleted = getUserById(id);
        if (userToBeDeleted == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }

        String deleteQuery = "DELETE FROM SystemUser WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement deletePs = conn.prepareStatement(deleteQuery)) {

            deletePs.setInt(1, id);
            deletePs.execute();
            System.out.println("User: " + userToBeDeleted.getUsername() + " has been deleted");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return userToBeDeleted;
    }

    @Override
    public User updateUserById(int id, String newUsername, String newPassword)
            throws EntityNotFoundException, EntityAlreadyRegisteredException {

        User toBeUpdated = getUserById(id);
        if (toBeUpdated == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }

        String updateQuery = "UPDATE SystemUser SET ";
        boolean updateNeeded = false;
        int paramIndex = 1;

        boolean usernameChanged = !newUsername.equals(toBeUpdated.getUsername());
        boolean passwordChanged = !newPassword.equals(toBeUpdated.getPassword());

        if (usernameChanged && !usernameIsAvailable(newUsername)) {
            throw new EntityAlreadyRegisteredException("Username already registered");
        }

        if (usernameChanged) {
            updateQuery += "username = ?";
            updateNeeded = true;
        }

        if (passwordChanged) {
            updateQuery += updateNeeded ? ", password = ?" : "password = ?";
            updateNeeded = true;
        }

        if (!updateNeeded) {
            return toBeUpdated;
        }

        updateQuery += " WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement updatePs = conn.prepareStatement(updateQuery)) {

            if (usernameChanged) {
                updatePs.setString(paramIndex++, newUsername);
            }

            if (passwordChanged) {
                updatePs.setString(paramIndex++, newPassword);
            }

            updatePs.setInt(paramIndex, id);
            updatePs.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return toBeUpdated;
    }

    private boolean usernameIsAvailable(String username) {
        String checkQuery = "SELECT COUNT(*) FROM SystemUser WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement checkPs = conn.prepareStatement(checkQuery)) {

            checkPs.setString(1, username);
            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }
}
