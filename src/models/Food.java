package models;

import models.enums.FoodType;

public class Food {
    
    private Long id;
    private String name;
    private FoodType type;

    public Food(String name, String type) {
        this.name = name;
        this.type = FoodType.valueOf(type.toUpperCase());
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

    @Override
    public String toString() {
        return "Food [id=" + id + ", name=" + name + ", type=" + type + "]";
    }

}
