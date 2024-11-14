package repositories;

import exceptions.EntityNotFoundException;
import models.DB;
import models.Food;
import models.FoodStock;
import models.Zoo;
import repositories.interfaces.IFoodStockRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public void createStock() {
        List<Food> foods = getFoodsInSystem();

        for (Food food : foods) {
            if (shouldCreateStock(food)) {
                createFoodStock(food);
            }
        }
    }

    @Override
    public void addFood(Long foodId, int amount) throws EntityNotFoundException {

        if (!checkFoodId(foodId)) {
            throw new EntityNotFoundException("Food not found with ID " + foodId);
        }

        if (amount < 0) {
            amount = 0;
        }

        int currentStock = getCurrentStockByFoodId(foodId);
        int finalStock = currentStock + amount;

        String addFoodQuery = "UPDATE FoodStock SET quantity = ? WHERE zoo_id = ? AND food_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement addFoodPs = conn.prepareStatement(addFoodQuery)) {

            addFoodPs.setInt(1, finalStock);
            addFoodPs.setLong(2, zoo.getZooId());
            addFoodPs.setLong(3, foodId);
            addFoodPs.executeUpdate();

            System.out.println("Stock for Food with ID " + foodId + " updated to " + finalStock + " in Zoo: " + zoo.getZooId());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void removeFood(Long foodId, int amount) throws EntityNotFoundException {

        if (!checkFoodId(foodId)) {
            throw new EntityNotFoundException("Food not found with ID " + foodId);
        }

        if (amount < 0) {
            amount = 0;
        }

        int currentStock = getCurrentStockByFoodId(foodId);

        if (currentStock < amount) {
            amount = currentStock;
        }

        int finalStock = currentStock - amount;

        String removeFoodQuery = "UPDATE FoodStock SET quantity = ? WHERE zoo_id = ? AND food_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement removeFoodPs = conn.prepareStatement(removeFoodQuery)) {

            removeFoodPs.setInt(1, finalStock);
            removeFoodPs.setLong(2, zoo.getZooId());
            removeFoodPs.setLong(3, foodId);
            removeFoodPs.executeUpdate();

            System.out.println("Stock for Food with ID " + foodId + " updated to " + finalStock + " in Zoo: " + zoo.getZooId());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<FoodStock> getFoodStock() {

        List<FoodStock> generalFoodStock = new ArrayList<>();
        String getFoodStockQuery = "SELECT * FROM FoodStock WHERE zoo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getFoodStockPs = conn.prepareStatement(getFoodStockQuery)) {

            getFoodStockPs.setLong(1, zoo.getZooId());

            try (ResultSet rs = getFoodStockPs.executeQuery()) {
                while (rs.next()) {
                    Long foodId = rs.getLong("food_id");
                    int quantity = rs.getInt("quantity");
                    generalFoodStock.add(new FoodStock(zoo.getZooId(), foodId, quantity));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return generalFoodStock;
    }


    private int getCurrentStockByFoodId(Long foodId) {
        int currentStock = 0;
        String getCurrentStockByFoodQuery = "SELECT * FROM FoodStock WHERE food_id = ? AND zoo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getCurrentStockByFoodPs = conn.prepareStatement(getCurrentStockByFoodQuery)) {

            getCurrentStockByFoodPs.setLong(1, foodId);
            getCurrentStockByFoodPs.setLong(2, zoo.getZooId());

            try (ResultSet rs = getCurrentStockByFoodPs.executeQuery()) {
                if (rs.next()) {
                    currentStock = rs.getInt("quantity");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return currentStock;
    }

    private boolean shouldCreateStock(Food food) {
        String checkStockQuery = "SELECT COUNT(*) FROM FoodStock WHERE zoo_id = ? AND food_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement checkStockPs = conn.prepareStatement(checkStockQuery)) {

            checkStockPs.setLong(1, zoo.getZooId());
            checkStockPs.setLong(2, food.getId());

            try (ResultSet rs = checkStockPs.executeQuery()) {
                rs.next();
                return rs.getInt(1) == 0;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    private void createFoodStock(Food food) {
        String createStockQuery = "INSERT INTO FoodStock (zoo_id, food_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement createStockPs = conn.prepareStatement(createStockQuery)) {

            createStockPs.setLong(1, zoo.getZooId());
            createStockPs.setLong(2, food.getId());
            createStockPs.setInt(3, 0);
            createStockPs.execute();

            System.out.println("Food stock created for: " + food.getName() + ", zoo: " + zoo.getZooId());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private List<Food> getFoodsInSystem() {
        List<Food> foods = new ArrayList<>();
        String getFoodsQuery = "SELECT * FROM Food";

        try (Connection conn = getConnection();
             PreparedStatement getFoodsSt = conn.prepareStatement(getFoodsQuery);
             ResultSet rs = getFoodsSt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("food_id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int price = rs.getInt("price");
                foods.add(new Food(id, name, type, price));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return foods;
    }

    private Boolean checkFoodId(Long foodId) {
        List<Food> foodsInSystem = getFoodsInSystem();
        for (Food food : foodsInSystem) {
            if (food.getId().equals(foodId)) {
                return true;
            }
        }
        return false;
    }
}
