package repositories;

import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import models.DB;
import models.Dinosaur;
import repositories.interfaces.IDinosaurRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DinosaurRepositoryImpl implements IDinosaurRepository {

    @Override
    public void createDinosaur(String species) {

    }

    @Override
    public List<Dinosaur> getDinosaurs() {
        return List.of();
    }

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
    public Dinosaur updateDinosaurById(int id, String newSpecies) throws EntityNotFoundException, EntityAlreadyRegisteredException {
        return null;
    }
}
