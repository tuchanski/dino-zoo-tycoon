package repositories;

import models.DB;
import models.User;
import models.Zoo;
import repositories.interfaces.IZooRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ZooRepositoryImpl implements IZooRepository {

    private User user; // Dependency Injection

    public ZooRepositoryImpl(User user) {
        this.user = user;
    }

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }


    @Override
    public void createZoo(String name, String location) {

        String createZooQuery = "INSERT INTO zoo (name, location, user_id) VALUES (?, ?, ?)";

        try {
            PreparedStatement createZooPs = getConnection().prepareStatement(createZooQuery);
            createZooPs.setString(1, name);
            createZooPs.setString(2, location);
            createZooPs.setLong(3, user.getId());

            createZooPs.execute();
            createZooPs.close();

            System.out.println("Zoo created: " + name);

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public List<Zoo> getAllZoos() {
        return List.of();
    }

    @Override
    public Zoo getZooById(Long id) {
        return null;
    }

    @Override
    public Zoo updateZooById(Long id, String newName, String newLocation) {
        return null;
    }

    @Override
    public Zoo deleteZooById(Long id) {
        return null;
    }
}
