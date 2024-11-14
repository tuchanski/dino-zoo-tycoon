package repositories;

import exceptions.EntityNotFoundException;
import exceptions.NotEnoughMoneyException;
import models.DB;
import models.User;
import models.Zoo;
import repositories.interfaces.IZooRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZooRepositoryImpl implements IZooRepository {

    private final User user;

    public ZooRepositoryImpl(User user) {
        this.user = user;
    }

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    @Override
    public void createZoo(String name) {
        String createZooQuery = "INSERT INTO zoo (name, cash, user_id) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement createZooPs = conn.prepareStatement(createZooQuery)) {

            createZooPs.setString(1, name);
            createZooPs.setInt(2, 0);
            createZooPs.setLong(3, user.getId());
            createZooPs.execute();
            System.out.println("Zoo created: " + name);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Zoo> getAllZoos() {
        List<Zoo> zoos = new ArrayList<>();
        String selectZooQuery = "SELECT * FROM zoo WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement selectZooPs = conn.prepareStatement(selectZooQuery)) {

            selectZooPs.setLong(1, user.getId());
            try (ResultSet rs = selectZooPs.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("zoo_id");
                    String name = rs.getString("name");
                    int cash = rs.getInt("cash");
                    zoos.add(new Zoo(id, name, cash, user.getId()));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return zoos;
    }

    @Override
    public Zoo getZooById(Long id) {
        String getZooQuery = "SELECT * FROM zoo WHERE zoo_id = ? AND user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getZooPs = conn.prepareStatement(getZooQuery)) {

            getZooPs.setLong(1, id);
            getZooPs.setLong(2, user.getId());
            try (ResultSet rs = getZooPs.executeQuery()) {
                if (rs.next()) {
                    return new Zoo(id, rs.getString("name"), rs.getInt("cash"), user.getId());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Zoo getZooByUser() {
        String getZooByUserQuery = "SELECT * FROM zoo WHERE user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getZooByUserPs = conn.prepareStatement(getZooByUserQuery)) {

            getZooByUserPs.setLong(1, user.getId());
            try (ResultSet rs = getZooByUserPs.executeQuery()) {
                if (rs.next()) {
                    Long id = rs.getLong("zoo_id");
                    String name = rs.getString("name");
                    int cash = rs.getInt("cash");
                    return new Zoo(id, name, cash, user.getId());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Zoo updateZooById(Long id, String newName) throws EntityNotFoundException {
        Zoo toBeUpdated = getZooById(id);
        if (toBeUpdated == null) {
            throw new EntityNotFoundException("Zoo with ID: " + id + " & User ID: " + user.getId() + " not found");
        }

        String updateQuery = "UPDATE Zoo SET ";
        boolean updateNeeded = false;
        int paramIndex = 1;

        boolean nameChanged = !newName.equals(toBeUpdated.getName());
        if (nameChanged) {
            updateQuery += "name = ?";
            updateNeeded = true;
        }
        if (!updateNeeded) {
            return toBeUpdated;
        }

        updateQuery += " WHERE zoo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement updatePs = conn.prepareStatement(updateQuery)) {

            if (nameChanged) {
                updatePs.setString(paramIndex++, newName);
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
        deleteZoo(id);
        return toBeDeleted;
    }

    private void deleteZoo(Long id) {
        String deleteZooQuery = "DELETE FROM zoo WHERE zoo_id = ? AND user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement deleteZooPs = conn.prepareStatement(deleteZooQuery)) {

            deleteZooPs.setLong(1, id);
            deleteZooPs.setLong(2, user.getId());
            deleteZooPs.execute();
            System.out.println("Zoo deleted: " + id);

        } catch (SQLException e) {
            System.out.println("Error while deleting zoo: " + e.getMessage());
        }
    }

    @Override
    public synchronized void addCash(Long id, int amount) throws EntityNotFoundException {
        Zoo toAddCash = getZooById(id);
        if (toAddCash == null) {
            throw new EntityNotFoundException("Zoo with ID: " + id + " & User ID: " + user.getId() + " not found");
        }

        int currentCash = getCurrentCash(id);
        int finalAmount = currentCash + Math.max(amount, 0);

        String addCashQuery = "UPDATE zoo SET cash = ? WHERE zoo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement addCashPs = conn.prepareStatement(addCashQuery)) {

            addCashPs.setLong(1, finalAmount);
            addCashPs.setLong(2, id);
            addCashPs.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public synchronized void removeCash(Long id, int amount) throws EntityNotFoundException {
        Zoo toRemoveCash = getZooById(id);
        if (toRemoveCash == null) {
            throw new EntityNotFoundException("Zoo with ID: " + id + " & User ID: " + user.getId() + " not found");
        }

        int currentCash = getCurrentCash(id);
        int finalAmount = currentCash - Math.max(amount, 0);

        if (finalAmount < 0) {
            System.out.println("Not enough cash to perform this operation.");
            return;
        }

        String removeCashQuery = "UPDATE zoo SET cash = ? WHERE zoo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement removeCashPs = conn.prepareStatement(removeCashQuery)) {

            removeCashPs.setLong(1, finalAmount);
            removeCashPs.setLong(2, id);
            removeCashPs.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Override
    public void contractNewEmployee(Long id) throws EntityNotFoundException, NotEnoughMoneyException {
        Zoo zoo = getZooById(id);
        if (zoo == null) {
            throw new EntityNotFoundException("Zoo with ID: " + id + " & User ID: " + user.getId() + " not found");
        }

        int currentCash = getCurrentCash(id);
        if (currentCash < 100) {
            throw new NotEnoughMoneyException("Zoo with ID: " + id + " does not have enough money.");
        }

        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(zoo);
        employeeRepository.createGenericEmployee();

        removeCash(id, 100);
    }

    public int getCurrentCash(Long id) {
        int currentCash = 0;
        String getCurrentCashQuery = "SELECT * FROM zoo WHERE zoo_id = ? AND user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getCurrentCashPs = conn.prepareStatement(getCurrentCashQuery)) {

            getCurrentCashPs.setLong(1, id);
            getCurrentCashPs.setLong(2, user.getId());
            try (ResultSet rs = getCurrentCashPs.executeQuery()) {
                if (rs.next()) {
                    currentCash = rs.getInt("cash");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return currentCash;
    }
}
