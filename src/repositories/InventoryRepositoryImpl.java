package repositories;

import models.DB;
import models.Inventory;
import repositories.interfaces.IInventoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepositoryImpl implements IInventoryRepository {

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    @Override
    public void createInventory(Long zooId) {
        String createInventoryQuery = "INSERT INTO Inventory (zoo_id) VALUES (?)";

        try {

            PreparedStatement createInventoryPs = getConnection().prepareStatement(createInventoryQuery);
            createInventoryPs.setLong(1, zooId);
            createInventoryPs.execute();
            createInventoryPs.close();
            System.out.println("Inventory created for Zoo ID: " + zooId);

        } catch (SQLException e) {
            System.out.println("Error creating inventory: " + e.getMessage());
        }
    }

    @Override
    public Inventory getInventoryById(Long id) {
        String getInventoryByIdQuery = "SELECT * FROM Inventory WHERE inventory_id = ?";

        try {

            PreparedStatement getInventoryByIdPs = getConnection().prepareStatement(getInventoryByIdQuery);
            getInventoryByIdPs.setLong(1, id);
            ResultSet rs = getInventoryByIdPs.executeQuery();

            if (rs.next()) {
                return new Inventory(rs.getLong("inventory_id"), rs.getLong("zoo_id"));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving inventory: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Inventory deleteInventoryById(Long id) {

        String deleteInventoryByIdQuery = "DELETE FROM Inventory WHERE inventory_id = ?";

        try {
            PreparedStatement deleteInventoryByIdPs = getConnection().prepareStatement(deleteInventoryByIdQuery);
            deleteInventoryByIdPs.setLong(1, id);
            deleteInventoryByIdPs.execute();
            deleteInventoryByIdPs.close();
            System.out.println("Inventory deleted with ID: " + id);
        } catch (SQLException e) {
            System.out.println("Error deleting inventory: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Inventory> getAllInventories() {

        List<Inventory> registeredInventoriesInSystem = new ArrayList<>();

        String getAllInventoriesQuery = "SELECT * FROM Inventory";

        try {

            PreparedStatement allInventoriesPs = getConnection().prepareStatement(getAllInventoriesQuery);
            ResultSet rs = allInventoriesPs.executeQuery();

            while (rs.next()) {
                Inventory inventory = new Inventory(rs.getLong("inventory_id"), rs.getLong("zoo_id"));
                registeredInventoriesInSystem.add(inventory);
            }

            allInventoriesPs.close();
            rs.close();

        } catch (SQLException e){
            System.out.println("Error retrieving all inventories: " + e.getMessage());
        }

        return registeredInventoriesInSystem;

    }
}
