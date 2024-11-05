package models.enums;

public enum DinosaurSpecies {

    ANKYLOSAURUS("ankylosaurus", "Herbivore"),
    APATOSAURUS("apatosaurus", "Herbivore"),
    BRACHIOSAURUS("brachiosaurus", "Herbivore"),
    CAMARASAURUS("camarasaurus", "Herbivore"),
    DIPLODOCUS("diplodocus", "Herbivore"),
    IGUANODON("iguanodon", "Herbivore"),
    PARASAUROLOPHUS("parasaurolophus", "Herbivore"),
    SAUROLOPHUS("saurolophus", "Herbivore"),
    STEGOSAURUS("stegosaurus", "Herbivore"),
    TRICERATOPS("triceratops", "Herbivore"),
    HADROSAURUS("hadrosaurus", "Herbivore"),
    SHANTUNGOSAURUS("shantungosaurus", "Herbivore"),
    CHASMOSAURUS("chasmosaurus", "Herbivore"),
    KENTROSAURUS("kentrosaurus", "Herbivore"),
    TOROSAURUS("torosaurus", "Herbivore"),

    TYRANNOSAURUS_REX("tyrannosaurus rex", "Carnivore"),
    VELOCIRAPTOR("velociraptor", "Carnivore"),
    SPINOSAURUS("spinosaurus", "Carnivore"),
    ALLOSAURUS("allosaurus", "Carnivore"),
    CARCHARODONTOSAURUS("carcharodontosaurus", "Carnivore"),
    GIGANOTOSAURUS("giganotosaurus", "Carnivore"),
    MEGALOSAURUS("megalosaurus", "Carnivore"),
    CERATOSAURUS("ceratosaurus", "Carnivore"),
    CARNOTAURUS("carnotaurus", "Carnivore"),
    DILOPHOSAURUS("dilophosaurus", "Carnivore"),
    COELOPHYSIS("coelophysis", "Carnivore"),
    COMPSOGNATHUS("compsognathus", "Carnivore"),
    ACROCANTHOSAURUS("acrocantosaurus", "Carnivore"),
    BARYONYX("baryonyx", "Carnivore"),
    THERIZINOSAURUS("therizinosaurus", "Carnivore"),

    GALLIMIMUS("gallimimus", "Omnivore"),
    ORNITHOMIMUS("ornithomimus", "Omnivore"),
    STRUTHIOMIMUS("struthiomimus", "Omnivore"),
    OVIRAPTOR("oviraptor", "Omnivore"),
    TROODON("troodon", "Omnivore"),
    SINORNITHOSAURUS("sinornithosaurus", "Omnivore");

    private final String name;
    private final String diet;

    DinosaurSpecies(String name, String diet) {
        this.name = name;
        this.diet = diet;
    }

    public String getName() {
        return name;
    }

    public String getDiet() {
        return diet;
    }
}
