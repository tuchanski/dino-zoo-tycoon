package models.enums;

public enum FoodType {

    MEAT("Meat", "Carnivore"),
    EGG("Egg", "Omnivore"),
    PLANT("Plant", "Herbivore");

    private final String name;
    private final String dietType;

    FoodType(String name, String dietType) {
        this.name = name;
        this.dietType = dietType;
    }

    public String getName() {
        return name;
    }

    public String getDietType() {
        return dietType;
    }

}
