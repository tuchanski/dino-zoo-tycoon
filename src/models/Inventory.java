package models;

public class Inventory {

    private Long inventoryId;
    private Long zooId;

    public Inventory(Long inventoryId, Long zooId) {
        this.inventoryId = inventoryId;
        this.zooId = zooId;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getZooId() {
        return zooId;
    }

    public void setZooId(Long zooId) {
        this.zooId = zooId;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", zooId=" + zooId +
                '}';
    }
}
