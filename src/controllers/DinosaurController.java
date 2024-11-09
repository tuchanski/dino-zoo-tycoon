package controllers;

import exceptions.EntityNotFoundException;
import exceptions.EntitySpeciesNotFound;
import models.Dinosaur;
import models.Zoo;
import repositories.DinosaurRepositoryImpl;
import repositories.interfaces.IDinosaurRepository;

import java.util.ArrayList;
import java.util.List;

public class DinosaurController {

    private IDinosaurRepository dinosaurRepository;

    public DinosaurController (Zoo zoo) {
        dinosaurRepository = new DinosaurRepositoryImpl(zoo);
    }

    public void createDinosaur(String species) {
        try {
            dinosaurRepository.createDinosaur(species);
        } catch (EntitySpeciesNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Dinosaur> getDinosaurs(){
        return dinosaurRepository.getDinosaurs();
    }

    public Dinosaur getDinosaurById(int id) {
        return dinosaurRepository.getDinosaurById(id);
    }

    public List<Dinosaur> getDinosaursBySpecies(String species) {

        List<Dinosaur> dinosaurs = new ArrayList<>();

        try {
            dinosaurs = dinosaurRepository.getDinosaursBySpecies(species);
        } catch (EntitySpeciesNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }

        return dinosaurs;
    }

    public Dinosaur deleteDinosaurById(int id) {

        Dinosaur dinosaur = null;

        try {
            dinosaur = dinosaurRepository.deleteDinosaurById(id);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return dinosaur;
    }

    public Dinosaur updateDinosaurById(int id, String newSpecies) {

        Dinosaur dinosaur = null;

        try {
            dinosaur = dinosaurRepository.updateDinosaurById(id, newSpecies);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return dinosaur;
    }

}
