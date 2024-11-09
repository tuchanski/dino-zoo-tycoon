package models;

import models.enums.FoodType;

public class Food {
    
    private Long id;
    private String name;
    private FoodType type;
    private int price;

    public Food(Long id, String name, FoodType type, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Food(String name, FoodType type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Food(Long id, String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.type = FoodType.valueOf(type.toUpperCase());
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
