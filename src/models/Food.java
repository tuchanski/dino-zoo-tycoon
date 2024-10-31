package models;

import models.enums.FoodType;

public class Food {

    private static Long idCounter = 0L;

    private Long id;
    private String name;
    private FoodType type;
    private Integer satietyLevel; // 1-10

    public Food(String name, String type, Integer satietyLevel) {
        this.id = ++idCounter;
        this.name = name;
        this.type = FoodType.valueOf(type.toUpperCase());
        setSatietyLevel(satietyLevel);
    }

    public Food(String name, String type) {
        this.id = ++idCounter;
        this.name = name;
        this.type = FoodType.valueOf(type.toUpperCase());
        this.satietyLevel = 5; // DEFAULT
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public Integer getSatietyLevel() {
        return satietyLevel;
    }

    public void setSatietyLevel(Integer satietyLevel) {
        if (satietyLevel < 1) {
            this.satietyLevel = 1;
        } else if (satietyLevel > 10) {
            this.satietyLevel = 10;
        } else {
            this.satietyLevel = satietyLevel;
        }
    }

    @Override
    public String toString() {
        return "Food [id=" + id + ", name=" + name + ", type=" + type + ", satietyLevel=" + satietyLevel + "]";
    }

}
