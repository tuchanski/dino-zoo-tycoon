package models;

public class Zoo {

    private Long zooId;
    private Long userId;

    private String name;
    private int cash;

    public Zoo(Long zooId, String name, Long userId) {
        this.zooId = zooId;
        this.name = name;
        this.userId = userId;
        this.cash = 0;
    }

    public Zoo(Long zooId, String name, int cash, Long userId) {
        this.zooId = zooId;
        this.name = name;
        this.userId = userId;
        this.cash = cash;
    }

    public Long getZooId() {
        return zooId;
    }

    public void setZooId(Long zooId) {
        this.zooId = zooId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "Zoo [id=" + zooId + ", userId=" + userId + ", name=" + name + "]";
    }

}
