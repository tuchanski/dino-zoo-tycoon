package models;

import models.enums.DinosaurSpecies;

public class Dinosaur {

    private Long id;
    private Long zooId;
    private DinosaurSpecies species;

    public Dinosaur(Long id, Long zooId, DinosaurSpecies species) {
        this.zooId = zooId;
        this.id = id;
        this.species = species;
    }

    public Dinosaur(Long id, String species) {
        this.id = id;
        this.species = DinosaurSpecies.valueOf(species.toUpperCase());
    }

    public Dinosaur(DinosaurSpecies species) {
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public String getDiet() {
        return species.getDiet();
    }

    public DinosaurSpecies getSpecies() {
        return species;
    }

    public void setSpecies(DinosaurSpecies species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Dinosaur{" +
                "id=" + id +
                ", species=" + species +
                '}';
    }

}
