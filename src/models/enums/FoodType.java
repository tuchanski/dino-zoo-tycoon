package models.enums;

public enum FoodType {

    MEAT("Meat"),
    EGG("Egg"),
    PLANT("Plant");

    private final String name;

    FoodType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
