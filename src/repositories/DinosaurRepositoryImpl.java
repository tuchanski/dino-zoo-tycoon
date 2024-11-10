package repositories;

import exceptions.EntityNotFoundException;
import exceptions.EntitySpeciesNotFoundException;
import models.DB;
import models.Dinosaur;
import models.Zoo;
import models.enums.DinosaurSpecies;
import repositories.interfaces.IDinosaurRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DinosaurRepositoryImpl implements IDinosaurRepository {

    private final Zoo zoo;

    public DinosaurRepositoryImpl(Zoo zoo) {
        this.zoo = zoo;
    }

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    @Override
    public void createDinosaur(String species) {

        String dietType = DinosaurSpecies.valueOf(species).getDiet();

        String createDinosaurQuery = "INSERT INTO dinosaur (species, diet_type, zoo_id) VALUES (?, ?, ?)";

        try {

            PreparedStatement createDinosaurPs = getConnection().prepareStatement(createDinosaurQuery);

            createDinosaurPs.setString(1, species);
            createDinosaurPs.setString(2, dietType);
            createDinosaurPs.setLong(3, zoo.getZooId());

            createDinosaurPs.execute();
            createDinosaurPs.close();

            System.out.println("Dinosaur " + species + " has been created successfully");

        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public List<Dinosaur> getDinosaurs() {

        List<Dinosaur> dinosaurs = new ArrayList<>();

        String getDinosaursQuery = "SELECT * FROM dinosaur WHERE zoo_id = ?";

        try {

            PreparedStatement getDinosaursPs = getConnection().prepareStatement(getDinosaursQuery);
            getDinosaursPs.setLong(1, zoo.getZooId());
            ResultSet getDinosaursRs = getDinosaursPs.executeQuery();

            while (getDinosaursRs.next()) {

                Long id = getDinosaursRs.getLong("dinosaur_id");
                DinosaurSpecies species = DinosaurSpecies.valueOf(getDinosaursRs.getString("species"));

                dinosaurs.add(new Dinosaur(id, zoo.getZooId(), species));
            }

            getDinosaursRs.close();
            getDinosaursPs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return dinosaurs;

    }

    @Override
    public List<Dinosaur> getDinosaursBySpecies(String species) throws EntitySpeciesNotFoundException {

        try {
            DinosaurSpecies.valueOf(species.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EntitySpeciesNotFoundException("Species not found");
        }
        
        List<Dinosaur> dinosaurs = new ArrayList<>();

        String getDinosaursQuery = "SELECT * FROM dinosaur WHERE species = ?";

        try {

            PreparedStatement getDinosaursPs = getConnection().prepareStatement(getDinosaursQuery);
            getDinosaursPs.setString(1, species);

            ResultSet getDinosaursRs = getDinosaursPs.executeQuery();

            while (getDinosaursRs.next()) {
                Long id = getDinosaursRs.getLong("dinosaur_id");
                dinosaurs.add(new Dinosaur(id, zoo.getZooId(), DinosaurSpecies.valueOf(species.toUpperCase())));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return dinosaurs;
    }

    @Override
    public Dinosaur getDinosaurById(int id) {

        Dinosaur dinosaur = null;
        String getDinosaurByIdQuery = "SELECT * FROM dinosaur WHERE dinosaur_id = ?";

        try {
            PreparedStatement getDinosaurByIdPs = getConnection().prepareStatement(getDinosaurByIdQuery);
            getDinosaurByIdPs.setInt(1, id);
            ResultSet rs = getDinosaurByIdPs.executeQuery();

            if (rs.next()) {

                Long dinosaurId = rs.getLong("dinosaur_id");
                DinosaurSpecies species = DinosaurSpecies.valueOf(rs.getString("species"));

                getDinosaurByIdPs.close();
                rs.close();

                dinosaur = new Dinosaur(dinosaurId, zoo.getZooId(),species);
            }

            getDinosaurByIdPs.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return dinosaur;
    }

    @Override
    public Dinosaur deleteDinosaurById(int id) throws EntityNotFoundException {

        Dinosaur dinosaurToBeDeleted = getDinosaurById(id);

        if (dinosaurToBeDeleted == null) {
            throw new EntityNotFoundException("Dinosaur not found: " + id);
        }

        try {
            String deleteQuery = "DELETE FROM dinosaur WHERE dinosaur_id = ?";
            PreparedStatement deletePs = getConnection().prepareStatement(deleteQuery);

            deletePs.setInt(1, id);
            deletePs.execute();
            deletePs.close();

            System.out.println("Dinosaur: " + dinosaurToBeDeleted.getId() + " has been deleted");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return dinosaurToBeDeleted;
    }

    @Override
    public Dinosaur updateDinosaurById(int id, String newSpecies) throws EntityNotFoundException {
        Dinosaur dinosaurToBeUpdated = getDinosaurById(id);

        if (dinosaurToBeUpdated == null) {
            throw new EntityNotFoundException("Dinosaur not found with id: " + id);
        }

        String updateQuery = "UPDATE dinosaur SET species = ? WHERE dinosaur_id = ?";

        try (PreparedStatement updatePs = getConnection().prepareStatement(updateQuery)) {

            updatePs.setString(1, newSpecies);
            updatePs.setInt(2, id);
            updatePs.executeUpdate();

            System.out.println("Dinosaur: " + id + " has been updated to " + newSpecies);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return new Dinosaur(Long.valueOf(id), zoo.getZooId(), DinosaurSpecies.valueOf(newSpecies));
    }
}
