package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.UserAlreadyRegisteredException;
import exceptions.UserNotFoundException;
import models.DB;
import models.User;
import repositories.interfaces.IUserRepository;

public class UserRepositoryImpl implements IUserRepository {

    @Override
    public void createUser(String username, String password) throws UserAlreadyRegisteredException {

        String checkQuery = "SELECT COUNT(*) FROM SystemUser WHERE username = ?";
        String insertQuery = "INSERT INTO SystemUser (username, password) VALUES (?, ?)";

        try {
            PreparedStatement checkPs = DB.connect().prepareStatement(checkQuery);
            checkPs.setString(1, username);
            ResultSet rs = checkPs.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                throw new UserAlreadyRegisteredException("Username already registered");
            }

            rs.close();
            checkPs.close();

            PreparedStatement insertPs = DB.connect().prepareStatement(insertQuery);
            insertPs.setString(1, username);
            insertPs.setString(2, password);

            insertPs.execute();
            insertPs.close();

            System.out.println("DONE");

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public List<User> getUsers() {

        List<User> registeredUsers = new ArrayList<>();

        String getUsersQuery = "SELECT * FROM SystemUser";

        try {
            PreparedStatement getUsersPs = DB.connect().prepareStatement(getUsersQuery);
            ResultSet rs = getUsersPs.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                registeredUsers.add(new User(username, password));
            }

            rs.close();
            getUsersPs.close();

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

        return registeredUsers;
    }

    @Override
    public User getUserById(int id) {
        String getUserByIdQuery = "SELECT * FROM SystemUser WHERE user_id = ?";

        try {
            PreparedStatement getUserByIdPs = DB.connect().prepareStatement(getUserByIdQuery);
            getUserByIdPs.setInt(1, id);
            ResultSet rs = getUserByIdPs.executeQuery();
            if (rs.next()) {
                Long userId = rs.getLong("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                return new User(userId, username, password);
            }

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public User getUserByUsername(String username) {

        String getUserByUsernameQuery = "SELECT * FROM SystemUser WHERE username = ?";

        try {
            PreparedStatement getUserByUsernamePs = DB.connect().prepareStatement(getUserByUsernameQuery);
            getUserByUsernamePs.setString(1, username);
            ResultSet rs = getUserByUsernamePs.executeQuery();
            if (rs.next()) {
                Long userId = rs.getLong("user_id");
                String password = rs.getString("password");
                return new User(userId, username, password);
            }

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public User deleteUserById(int id) throws UserNotFoundException {

        String checkQuery = "SELECT COUNT(*) FROM SystemUser WHERE user_id = ?";
        User userToBeDeleted = getUserById(id);

        try {

            if (userToBeDeleted == null) {
                throw new UserNotFoundException("User not found with id: " + id);
            }

            String deleteQuery = "DELETE FROM SystemUser WHERE user_id = ?";
            PreparedStatement deletePs = DB.connect().prepareStatement(deleteQuery);
            deletePs.setInt(1, id);
            deletePs.execute();
            deletePs.close();
            System.out.println("DONE");

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return userToBeDeleted;
    }

    @Override
    public User updateUserById(int id, String newUsername, String newPassword)
            throws UserNotFoundException, UserAlreadyRegisteredException {

        User toBeUpdated = getUserById(id);

        try {

            if (toBeUpdated == null) {
                throw new UserNotFoundException("User not found with id: " + id);
            }

            if (!newUsername.equals(toBeUpdated.getUsername()) && usernameIsAvailable(newUsername)) {
                throw new UserAlreadyRegisteredException("Username is already registered: " + newUsername);
            }

            if (!usernameIsAvailable(newUsername)){
                throw new UserAlreadyRegisteredException("Username is already registered: " + newUsername);
            }

            String updateQuery = "UPDATE SystemUser SET username = ?, password = ? WHERE user_id = ?";
            PreparedStatement updatePs = DB.connect().prepareStatement(updateQuery);
            updatePs.setString(1, newUsername);
            updatePs.setString(2, newPassword);
            updatePs.setInt(3, id);
            updatePs.executeUpdate();
            updatePs.close();

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

        return toBeUpdated;
    }

    private boolean usernameIsAvailable(String username) {
        String checkQuery = "SELECT COUNT(*) FROM SystemUser WHERE username = ?";
        try {
            PreparedStatement checkPs = DB.connect().prepareStatement(checkQuery);
            checkPs.setString(1, username);
            ResultSet rs = checkPs.executeQuery();
            while (rs.next()){
                if (rs.getInt(1) > 0) {
                    rs.close();
                    checkPs.close();
                    return false;
                }
            }
            rs.close();
            checkPs.close();

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

}
