package models.abstracts;

public abstract class Human {

    private Long id;
    private String name;
    private Long zooId;

    public Human(Long id, String name, Long zooId) {
        this.id = id;
        this.name = name;
        this.zooId = zooId;
    }

    public Human(String name) {
        this.name = name;
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

    public abstract String getDailyTask();

    public String getRole() {
        return "General Human";
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zooId=" + zooId +
                '}';
    }
}
