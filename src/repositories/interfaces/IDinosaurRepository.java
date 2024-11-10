package repositories.interfaces;

import exceptions.EntityNotFoundException;
import exceptions.EntitySpeciesNotFoundException;
import models.Dinosaur;

import java.util.List;

public interface IDinosaurRepository {
    void createDinosaur(String species) throws EntitySpeciesNotFoundException;
    List<Dinosaur> getDinosaurs();
    List<Dinosaur> getDinosaursBySpecies(String species) throws EntitySpeciesNotFoundException;
    Dinosaur getDinosaurById(int id);
    Dinosaur deleteDinosaurById(int id) throws EntityNotFoundException;
    Dinosaur updateDinosaurById(int id, String newSpecies) throws EntityNotFoundException;
}
