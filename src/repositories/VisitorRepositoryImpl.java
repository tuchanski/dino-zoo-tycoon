package repositories;

import models.DB;
import models.Visitor;
import models.Zoo;
import repositories.interfaces.IVisitorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VisitorRepositoryImpl implements IVisitorRepository {

    private final Zoo zoo;

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
    public void createVisitor(String name) {

    }

    @Override
    public List<Visitor> getVisitors(Long zooId) {
        return List.of();
    }

    @Override
    public Visitor getVisitor(Long visitorId) {
        return null;
    }

    @Override
    public Visitor deleteVisitor(Long visitorId) {
        return null;
    }

    private String getRandomName() {
        List<String> names = Arrays.asList("Afonso", "Guilherme", "Luiz", "Fernanda", "Gabriela", "Marina", "Rafael", "Felipe", "Bruno", "Alice", "Ana", "Carla");
        Random random = new Random();
        return names.get(random.nextInt(names.size()));
    }

}
