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

        List<Food> foods = new ArrayList<>();
        String getFoodsQuery = "SELECT * FROM Food";

        try {

            PreparedStatement getFoodsPs = getConnection().prepareStatement(getFoodsQuery);
            ResultSet rs = getFoodsPs.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("food_id");
                String name = rs.getString("name");
                String typeToString = rs.getString("type");
                int price = rs.getInt("price");
                foods.add(new Food(id, name, typeToString, price));
            }

            getFoodsPs.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return foods;

    }

    @Override
    public Food getFoodById(Long id) {

        Food food = null;
        String getFoodByIdQuery = "SELECT * FROM Food WHERE id = ?";

        try {

            PreparedStatement getFoodByIdPs = getConnection().prepareStatement(getFoodByIdQuery);
            getFoodByIdPs.setLong(1, id);

            ResultSet rs = getFoodByIdPs.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                food = new Food(id, name, FoodType.valueOf(type.toUpperCase()), price);
            }

            rs.close();
            getFoodByIdPs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return food;
    }

    @Override
    public Food getFoodByName(String name) {

        Food food = null;

        String getFoodByNameQuery = "SELECT * FROM Food WHERE name = ?";

        try {

            PreparedStatement getFoodByNamePs = getConnection().prepareStatement(getFoodByNameQuery);
            getFoodByNamePs.setString(1, name);

            ResultSet rs = getFoodByNamePs.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("food_id");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                food = new Food(id, name, FoodType.valueOf(type.toUpperCase()), price);
            }


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return food;

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
