package models.enums;

public enum DinosaurType {
    CARNIVORE("Carnivore"),
    HERBIVORE("Herbivore"),
    OMNIVORE("Omnivore");

    private final String name;

    DinosaurType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
