package repositories;

import exceptions.EntityAlreadyRegisteredException;
import models.DB;
import models.Food;
import models.enums.FoodType;
import repositories.interfaces.IFoodRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        try {

            PreparedStatement createFoodPs = getConnection().prepareStatement(createFoodQuery);
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
        return List.of();
    }

    @Override
    public Food getFoodById(Long id) {
        return null;
    }

    @Override
    public Food getFoodByName(String name) {
        return null;
    }

    @Override
    public void updateFood(Long id, String newName, FoodType newType, int newPrice) {

    }

    @Override
    public void deleteFood(Long id) {

    }

    private Boolean foodNameIsAvailable(String name) {

        boolean nameIsAvailable = true;
        String foodNameIsAvailableQuery = "SELECT * FROM Food WHERE name = ?";

        try {

            PreparedStatement foodNameIsAvailablePs = getConnection().prepareStatement(foodNameIsAvailableQuery);
            foodNameIsAvailablePs.setString(1, name);
            ResultSet rs = foodNameIsAvailablePs.executeQuery();

            if (rs.next()) {
                nameIsAvailable = false;
            }

            foodNameIsAvailablePs.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return nameIsAvailable;

    }
}
