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
        String createGenericVisitorQuery = "INSERT INTO visitor (name, zoo_id) VALUES (?, ?)";

        try {

            PreparedStatement createGenericVisitorPs = getConnection().prepareStatement(createGenericVisitorQuery);
            createGenericVisitorPs.setString(1, name);
            createGenericVisitorPs.setLong(2, zoo.getZooId());

            createGenericVisitorPs.execute();
            createGenericVisitorPs.close();

            System.out.println("Visitor " + name + " has been created successfully to zoo: " + zoo.getName());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public List<Visitor> getVisitors() {

        List<Visitor> visitors = new ArrayList<>(); // May be empty

        String getVisitorsQuery = "SELECT * FROM visitor WHERE zoo_id = ?";

        try {

            PreparedStatement getVisitorPs = getConnection().prepareStatement(getVisitorsQuery);
            getVisitorPs.setLong(1, zoo.getZooId());
            ResultSet rs = getVisitorPs.executeQuery();

            while (rs.next()) {
                Long visitorId = rs.getLong("visitor_id");
                String name = rs.getString("name");
                visitors.add(new Visitor(visitorId, name, zoo.getZooId()));
            }

            getVisitorPs.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return visitors;

    }

    @Override
    public Visitor getVisitorById(Long visitorId) {

        Visitor visitor = null;

        String getVisitorQuery = "SELECT * FROM visitor WHERE visitor_id = ? AND zoo_id = ?";

        try {

            PreparedStatement getVisitorPs = getConnection().prepareStatement(getVisitorQuery);
            getVisitorPs.setLong(1, visitorId);
            getVisitorPs.setLong(2, zoo.getZooId());

            ResultSet rs = getVisitorPs.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                visitor = new Visitor(visitorId, name, zoo.getZooId());
            }

            rs.close();
            getVisitorPs.close();

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

        try {

            PreparedStatement deleteVisitorPs = getConnection().prepareStatement(deleteVisitorQuery);
            deleteVisitorPs.setLong(1, visitorId);
            deleteVisitorPs.setLong(2, zoo.getZooId());
            deleteVisitorPs.execute();
            deleteVisitorPs.close();

            System.out.println("Visitor with ID" + visitor.getId() + " has been deleted successfully to zoo: " + zoo.getName());

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
