package repositories.interfaces;

import exceptions.EntityAlreadyRegisteredException;
import exceptions.EntityNotFoundException;
import exceptions.EntitySpeciesNotFound;
import models.Dinosaur;

import java.util.List;

public interface IDinosaurRepository {
    void createDinosaur(String species) throws EntitySpeciesNotFound;
    List<Dinosaur> getDinosaurs();
    Dinosaur getDinosaurById(int id);
    List<Dinosaur> getDinosaursBySpecies(String species) throws EntitySpeciesNotFound;
    Dinosaur deleteDinosaurById(int id) throws EntityNotFoundException;
    Dinosaur updateDinosaurById(int id, String newSpecies) throws EntityNotFoundException;
}
