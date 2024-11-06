package repositories;

import exceptions.EntityNotFoundException;
import models.DB;
import models.Dinosaur;
import models.enums.DinosaurSpecies;
import repositories.interfaces.IDinosaurRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DinosaurRepositoryImpl implements IDinosaurRepository {

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    @Override
    public void createDinosaur(String species) {

        //int enclosureId = 1; // CHANGE AFTER CREATING ENCLOSURE'S LOGIC

        String dietType = DinosaurSpecies.valueOf(species).getDiet();

        String createDinosaurQuery = "INSERT INTO dinosaur (species, diet_type, enclosure_id) VALUES (?, ?, ?)";

        try {

            PreparedStatement createDinosaurPs = getConnection().prepareStatement(createDinosaurQuery);

            createDinosaurPs.setString(1, species);
            createDinosaurPs.setString(2, dietType);
            //createDinosaurPs.setLong(3, enclosureId);

            createDinosaurPs.execute();
            createDinosaurPs.close();

            System.out.println("Dinosaur " + species + " has been created successfully");

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public List<Dinosaur> getDinosaurs() {

        List<Dinosaur> dinosaurs = new ArrayList<>(); // May be empty

        String getDinosaursQuery = "SELECT * FROM dinosaur";

        try {

            PreparedStatement getDinosaursPs = getConnection().prepareStatement(getDinosaursQuery);
            ResultSet getDinosaursRs = getDinosaursPs.executeQuery();

            while (getDinosaursRs.next()) {

                Long id = getDinosaursRs.getLong("dinosaur_id");
                DinosaurSpecies species = DinosaurSpecies.valueOf(getDinosaursRs.getString("species"));

                dinosaurs.add(new Dinosaur(id, species));
            }

            getDinosaursRs.close();
            getDinosaursPs.close();

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

        return dinosaurs;

    }

    // To implement

    @Override
    public Dinosaur getDinosaurById(int id) {
        return null;
    }

    @Override
    public List<Dinosaur> getDinosaursBySpecies(String species) {
        return List.of();
    }

    @Override
    public Dinosaur deleteDinosaurById(int id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Dinosaur updateDinosaurById(int id, String newSpecies) throws EntityNotFoundException {
        return null;
    }
}
