package models;

public class Cash {

    private Long id;
    private int quantity;

    public Cash (Long id, int quantity) {
        this.id = id;
        this.quantity = 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        quantity += amount;
    }

    public void removeQuantity(int amount) {
        quantity -= amount;
    }

    public Long getId() {
        return id;
    }

}
