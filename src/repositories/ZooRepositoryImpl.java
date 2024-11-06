package repositories;

import exceptions.EntityNotFoundException;
import models.DB;
import models.User;
import models.Zoo;
import repositories.interfaces.IInventoryRepository;
import repositories.interfaces.IZooRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZooRepositoryImpl implements IZooRepository {

    private final User user; // Dependency Injection
    private final IInventoryRepository inventoryRepository = new InventoryRepositoryImpl();

    public ZooRepositoryImpl(User user) {
        this.user = user;
    }

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    @Override
    public void createZoo(String name, String location) {

        String createZooQuery = "INSERT INTO zoo (name, location, user_id) VALUES (?, ?, ?) RETURNING zoo_id";

        try {
            PreparedStatement createZooPs = getConnection().prepareStatement(createZooQuery);
            createZooPs.setString(1, name);
            createZooPs.setString(2, location);
            createZooPs.setLong(3, user.getId());

            ResultSet rs = createZooPs.executeQuery();
            if (rs.next()) {
                Long zooId = rs.getLong("zoo_id");
                System.out.println("Zoo created: " + name);

                inventoryRepository.createInventory(zooId);
            }

            rs.close();
            createZooPs.close();

            System.out.println("Zoo created: " + name);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public List<Zoo> getAllZoos() {

        List<Zoo> zoos = new ArrayList<>();

        String selectZooQuery = "SELECT * FROM zoo where user_id = ?";

        try {
            PreparedStatement selectZooPs = getConnection().prepareStatement(selectZooQuery);
            selectZooPs.setLong(1, user.getId());
            ResultSet rs = selectZooPs.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("zoo_id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                zoos.add(new Zoo(id, name, location, user.getId()));
            }

            rs.close();
            selectZooPs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return zoos;

    }

    @Override
    public Zoo getZooById(Long id) {

        String getZooQuery = "SELECT * FROM zoo WHERE zoo_id = ? AND user_id = ?";

        try {
            PreparedStatement getZooPs = getConnection().prepareStatement(getZooQuery);
            getZooPs.setLong(1, id);
            getZooPs.setLong(2, user.getId());
            ResultSet rs = getZooPs.executeQuery();
            if (rs.next()) {
                return new Zoo(id, rs.getString("name"), rs.getString("location"), user.getId());
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Zoo updateZooById(Long id, String newName, String newLocation)
            throws EntityNotFoundException {

        Zoo toBeUpdated = getZooById(id);

        if (toBeUpdated == null) {
            throw new EntityNotFoundException("Zoo with ID: " + id + " & User ID: " + user.getId() + " not found");
        }

        String updateQuery = "UPDATE Zoo SET ";
        boolean updateNeeded = false;
        int paramIndex = 1;

        boolean nameChanged = !newName.equals(toBeUpdated.getName());
        boolean locationChanged = !newLocation.equals(toBeUpdated.getLocation());

        if (nameChanged) {
            updateQuery += "name = ?";
            updateNeeded = true;
        }

        if (locationChanged) {
            updateQuery += updateNeeded ? ", location = ?" : "location = ?";
            updateNeeded = true;
        }

        if (!updateNeeded) {
            return toBeUpdated;
        }

        updateQuery += " WHERE zoo_id = ?";

        try (PreparedStatement updatePs = getConnection().prepareStatement(updateQuery)) {

            if (nameChanged) {
                updatePs.setString(paramIndex++, newName);
            }

            if (locationChanged) {
                updatePs.setString(paramIndex++, newLocation);
            }

            updatePs.setLong(paramIndex, id);
            updatePs.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return toBeUpdated;
    }


    @Override
    public Zoo deleteZooById(Long id) throws EntityNotFoundException {

        Zoo toBeDeleted = getZooById(id);

        if (toBeDeleted == null) {
            throw new EntityNotFoundException("Zoo with ID: " + id + " & User ID: " + user.getId() + " not found");
        }

        String deleteZooQuery = "DELETE FROM zoo WHERE zoo_id = ? AND user_id = ?";

        try {

            PreparedStatement deleteZooPs = getConnection().prepareStatement(deleteZooQuery);
            deleteZooPs.setLong(1, id);
            deleteZooPs.setLong(2, user.getId());
            deleteZooPs.execute();
            deleteZooPs.close();
            System.out.println("Zoo deleted: " + id);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return toBeDeleted;
    }
}
