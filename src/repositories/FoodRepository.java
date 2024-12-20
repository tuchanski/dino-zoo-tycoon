package repositories;

import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.DB;
import models.Food;
import models.enums.FoodType;
import repositories.interfaces.IFoodRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository implements IFoodRepository {

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    @Override
    public void createFood(String name, FoodType type, int price) throws EntityAlreadyRegisteredException {

        if (!foodNameIsAvailable(name)) {
            throw new EntityAlreadyRegisteredException("Food name already registered");
        }

        String createFoodQuery = "INSERT INTO Food (name, type, price) VALUES (?, ?, ?)";
        String typeToString = type.toString();

        try (Connection conn = getConnection();
             PreparedStatement createFoodPs = conn.prepareStatement(createFoodQuery)) {

            createFoodPs.setString(1, name);
            createFoodPs.setString(2, typeToString);
            createFoodPs.setInt(3, price);
            createFoodPs.execute();

            System.out.println("Food " + name + " has been created successfully");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Food> getFoods() {

        List<Food> foods = new ArrayList<>();
        String getFoodsQuery = "SELECT * FROM Food";

        try (Connection conn = getConnection();
             PreparedStatement getFoodsPs = conn.prepareStatement(getFoodsQuery);
             ResultSet rs = getFoodsPs.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("food_id");
                String name = rs.getString("name");
                String typeToString = rs.getString("type");
                int price = rs.getInt("price");
                foods.add(new Food(id, name, typeToString, price));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return foods;
    }

    @Override
    public Food getFoodById(Long id) {

        Food food = null;
        String getFoodByIdQuery = "SELECT * FROM Food WHERE food_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getFoodByIdPs = conn.prepareStatement(getFoodByIdQuery)) {

            getFoodByIdPs.setLong(1, id);

            try (ResultSet rs = getFoodByIdPs.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    int price = rs.getInt("price");
                    food = new Food(id, name, FoodType.valueOf(type.toUpperCase()), price);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return food;
    }

    @Override
    public Food getFoodByName(String name) {

        Food food = null;
        String getFoodByNameQuery = "SELECT * FROM Food WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement getFoodByNamePs = conn.prepareStatement(getFoodByNameQuery)) {

            getFoodByNamePs.setString(1, name);

            try (ResultSet rs = getFoodByNamePs.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong("food_id");
                    String type = rs.getString("type");
                    int price = rs.getInt("price");
                    food = new Food(id, name, FoodType.valueOf(type.toUpperCase()), price);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return food;
    }

    @Override
    public Food updateFoodById(Long id, String newName, FoodType newType, int newPrice) throws EntityAlreadyRegisteredException, EntityNotFoundException {
        Food toBeUpdated = getFoodById(id);

        if (toBeUpdated == null) {
            throw new EntityNotFoundException("Food not found with id: " + id);
        }

        if (!newName.equals(toBeUpdated.getName()) && !foodNameIsAvailable(newName)) {
            throw new EntityAlreadyRegisteredException("Food name already registered");
        }

        String updateQuery = "UPDATE Food SET name = ?, type = ?, price = ? WHERE food_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement updatePs = conn.prepareStatement(updateQuery)) {

            updatePs.setString(1, newName);
            updatePs.setString(2, newType.toString());
            updatePs.setInt(3, newPrice);
            updatePs.setLong(4, id);
            updatePs.executeUpdate();

            toBeUpdated.setName(newName);
            toBeUpdated.setType(newType);
            toBeUpdated.setPrice(newPrice);

            System.out.println("Food " + id + " has been updated successfully");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return toBeUpdated;
    }

    @Override
    public Food deleteFoodById(Long id) throws EntityNotFoundException {

        Food toBeDeleted = getFoodById(id);

        if (toBeDeleted == null) {
            throw new EntityNotFoundException("Food not found");
        }

        String deleteFoodQuery = "DELETE FROM Food WHERE food_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement deleteFoodPs = conn.prepareStatement(deleteFoodQuery)) {

            deleteFoodPs.setLong(1, id);
            deleteFoodPs.execute();

            System.out.println("Food " + id + " has been deleted successfully");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return toBeDeleted;
    }

    private Boolean foodNameIsAvailable(String name) {

        boolean nameIsAvailable = true;
        String foodNameIsAvailableQuery = "SELECT * FROM Food WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement foodNameIsAvailablePs = conn.prepareStatement(foodNameIsAvailableQuery)) {

            foodNameIsAvailablePs.setString(1, name);

            try (ResultSet rs = foodNameIsAvailablePs.executeQuery()) {
                if (rs.next()) {
                    nameIsAvailable = false;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return nameIsAvailable;
    }


}
