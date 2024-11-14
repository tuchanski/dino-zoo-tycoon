package repositories;

import exceptions.EntityNotFoundException;
import models.DB;
import models.Visitor;
import models.Zoo;
import repositories.interfaces.IVisitorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VisitorRepositoryImpl implements IVisitorRepository {

    private final Zoo zoo;

    private List<String> names = Arrays.asList("Afonso", "Guilherme", "Luiz", "Fernanda",
            "Gabriela", "Marina", "Rafael", "Felipe", "Bruno", "Alice", "Ana", "Carla",
            "Arion", "Gabriel", "Joaquim", "Raquel", "Jurema", "Sofia");

    public VisitorRepositoryImpl(Zoo zoo) {
        this.zoo = zoo;
    }

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    @Override
    public void createGenericVisitor() {
        String name = getRandomName();
        String dailyTask = new Visitor(null, name, zoo.getZooId()).getDailyTask();
        String createGenericVisitorQuery = "INSERT INTO visitor (name, zoo_id, daily_task) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement createGenericVisitorPs = conn.prepareStatement(createGenericVisitorQuery)) {

            createGenericVisitorPs.setString(1, name);
            createGenericVisitorPs.setLong(2, zoo.getZooId());
            createGenericVisitorPs.setString(3, dailyTask);

            createGenericVisitorPs.execute();
            System.out.println("Visitor " + name + " has been created successfully to zoo: " + zoo.getName());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getDailyTask(Long visitorId) {
        String dailyTask = null;
        String getDailyTaskQuery = "SELECT * FROM visitor WHERE visitor_id = ? AND zoo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getDailyTaskPs = conn.prepareStatement(getDailyTaskQuery)) {

            getDailyTaskPs.setLong(1, visitorId);
            getDailyTaskPs.setLong(2, zoo.getZooId());
            try (ResultSet rs = getDailyTaskPs.executeQuery()) {
                if (rs.next()) {
                    dailyTask = rs.getString("daily_task");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return dailyTask;
    }

    @Override
    public List<Visitor> getVisitors() {
        List<Visitor> visitors = new ArrayList<>();
        String getVisitorsQuery = "SELECT * FROM visitor WHERE zoo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getVisitorPs = conn.prepareStatement(getVisitorsQuery)) {

            getVisitorPs.setLong(1, zoo.getZooId());
            try (ResultSet rs = getVisitorPs.executeQuery()) {
                while (rs.next()) {
                    Long visitorId = rs.getLong("visitor_id");
                    String name = rs.getString("name");
                    visitors.add(new Visitor(visitorId, name, zoo.getZooId()));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return visitors;
    }

    @Override
    public Visitor getVisitorById(Long visitorId) {
        Visitor visitor = null;
        String getVisitorQuery = "SELECT * FROM visitor WHERE visitor_id = ? AND zoo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement getVisitorPs = conn.prepareStatement(getVisitorQuery)) {

            getVisitorPs.setLong(1, visitorId);
            getVisitorPs.setLong(2, zoo.getZooId());

            try (ResultSet rs = getVisitorPs.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    visitor = new Visitor(visitorId, name, zoo.getZooId());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return visitor;
    }

    @Override
    public Visitor deleteVisitorById(Long visitorId) throws EntityNotFoundException {
        Visitor visitor = getVisitorById(visitorId);

        if (visitor == null) {
            throw new EntityNotFoundException("Visitor not found with ID: " + visitorId);
        }

        String deleteVisitorQuery = "DELETE FROM visitor WHERE visitor_id = ? AND zoo_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement deleteVisitorPs = conn.prepareStatement(deleteVisitorQuery)) {

            deleteVisitorPs.setLong(1, visitorId);
            deleteVisitorPs.setLong(2, zoo.getZooId());
            deleteVisitorPs.execute();

            System.out.println("Visitor with ID " + visitor.getId() + " has been deleted successfully from zoo: " + zoo.getName());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return visitor;
    }

    private String getRandomName() {
        Random random = new Random();
        return names.get(random.nextInt(names.size()));
    }
}
