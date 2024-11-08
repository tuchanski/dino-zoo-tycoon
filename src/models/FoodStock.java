package models;

import models.enums.FoodType;

public class FoodStock {

    private Long zooId;
    private FoodType foodType;
    private int quantity;

    public FoodStock(Long zooId, FoodType foodType) {
        this.zooId = zooId;
        this.foodType = foodType;
        this.quantity = 0;
    }

    public FoodStock(Long zooId, String foodType) {
        this.zooId = zooId;
        this.foodType = FoodType.valueOf(foodType.toUpperCase()); // MEAT, EGG, PLANT
        this.quantity = 0;
    }

    public FoodStock(Long zooId, FoodType foodType, int quantity) {
        this.zooId = zooId;
        this.foodType = foodType;
        this.quantity = quantity;
    }

    public FoodStock(Long zooId, String foodType, int quantity) {
        this.zooId = zooId;
        this.foodType = FoodType.valueOf(foodType.toUpperCase()); // MEAT, EGG, PLANT
        this.quantity = quantity;
    }

    public Long getZooId() {
        return zooId;
    }

    public void setZooId(Long zooId) {
        this.zooId = zooId;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "FoodStock{" +
                "zooId=" + zooId +
                ", foodType=" + foodType +
                ", quantity=" + quantity +
                '}';
    }
}
