package controllers;

import exceptions.EntityNotFoundException;
import exceptions.EntitySpeciesNotFound;
import models.Dinosaur;
import models.Zoo;
import repositories.DinosaurRepositoryImpl;
import repositories.interfaces.IDinosaurRepository;

import java.util.List;

public class DinosaurController {

    private IDinosaurRepository dinosaurRepository;

    public DinosaurController (Zoo zoo) {
        dinosaurRepository = new DinosaurRepositoryImpl(zoo);
    }

    public void createDinosaur(String species) throws EntitySpeciesNotFound {
        dinosaurRepository.createDinosaur(species);
    }

    public List<Dinosaur> getDinosaurs(){
        return dinosaurRepository.getDinosaurs();
    }

    public Dinosaur getDinosaurById(int id) throws EntityNotFoundException {
        return dinosaurRepository.getDinosaurById(id);
    }

    public List<Dinosaur> getDinosaursBySpecies(String species) throws EntitySpeciesNotFound {
        return dinosaurRepository.getDinosaursBySpecies(species);
    }

    public Dinosaur deleteDinosaurById(int id) throws EntityNotFoundException {
        return dinosaurRepository.deleteDinosaurById(id);
    }

    public Dinosaur updateDinosaurById(int id, String newSpecies) throws EntityNotFoundException {
        return dinosaurRepository.updateDinosaurById(id, newSpecies);
    }


}
