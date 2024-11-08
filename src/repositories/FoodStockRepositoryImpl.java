package repositories;

import models.DB;
import models.FoodStock;
import models.Zoo;
import models.enums.FoodType;
import repositories.interfaces.IFoodStockRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FoodStockRepositoryImpl implements IFoodStockRepository {

    private final Zoo zoo;

    public FoodStockRepositoryImpl(Zoo zoo) {
        this.zoo = zoo;
        createStock();
    }

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    private void createStock() {
        String genericQuery = "INSERT INTO FoodStock (zoo_id, name, type) VALUES (?, ?, ?)";

        try {



        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void addFood(FoodType foodType, int amount) {

        if (amount < 0) {
            amount = 0;
        }

        int currentStock = getCurrentStockByFoodType(foodType);
        int finalStock = currentStock + amount;

        String addFoodQuery = "UPDATE FoodStock SET quantity = ? WHERE zoo_id = ? AND food_type = ?";

        try {
            PreparedStatement addFoodPs = getConnection().prepareStatement(addFoodQuery);
            addFoodPs.setInt(1, finalStock);
            addFoodPs.setLong(2, zoo.getZooId());
            addFoodPs.setString(3, foodType.getName());
            addFoodPs.executeUpdate();
            addFoodPs.close();

            System.out.println("Stock for " + foodType.getName() + " updated: " + finalStock);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public void removeFood(FoodType foodType, int amount) {

    }

    @Override
    public List<FoodStock> getFoodStock() {
        return List.of();
    }

    @Override
    public FoodStock getFoodStockByType(FoodType foodType) {
        return null;
    }

    private int getCurrentStockByFoodType(FoodType foodType) {
        int currentStock = 0;

        String getCurrentStockByFoodQuery = "SELECT * FROM FoodStock WHERE food_type = ? AND zoo_id = ?";

        try {
            PreparedStatement getCurrentStockByFoodPs = getConnection().prepareStatement(getCurrentStockByFoodQuery);
            getCurrentStockByFoodPs.setString(1, foodType.toString());
            getCurrentStockByFoodPs.setLong(2, zoo.getZooId());

            ResultSet rs = getCurrentStockByFoodPs.executeQuery();

            if (rs.next()) {
                currentStock = rs.getInt("quantity");
            }

            getCurrentStockByFoodPs.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return currentStock;
    }
}
