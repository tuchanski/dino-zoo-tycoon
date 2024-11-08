package models;

import models.enums.FoodType;

public class FoodStock {

    private Long zooId;
    private Long foodId;
    private int quantity;

    public FoodStock(Long zooId, Long foodId, int quantity) {
        this.zooId = zooId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public Long getZooId() {
        return zooId;
    }

    public void setZooId(Long zooId) {
        this.zooId = zooId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getFoodId() {
        return foodId;
    }

    @Override
    public String toString() {
        return "FoodStock{" +
                "zooId=" + zooId +
                ", foodId=" + foodId +
                ", quantity=" + quantity +
                '}';
    }
}
