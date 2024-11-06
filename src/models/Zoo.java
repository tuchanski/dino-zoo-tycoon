package models;

public class Zoo {

    private Long zooId;
    private String name;
    private String location;

    private Long userId;

    public Zoo(Long zooId, String name, String location, Long userId) {
        this.zooId = zooId;
        this.name = name;
        this.location = location;
        this.userId = userId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "zooId=" + zooId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", userId=" + userId +
                '}';
    }
}
