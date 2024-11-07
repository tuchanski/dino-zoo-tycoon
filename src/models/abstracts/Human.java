package models.abstracts;

public abstract class Human {

    private static Long idCounter = 0L;

    private Long id;

    private String name;

    public Human(String name) {
        idCounter++;
        this.id = idCounter;
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

    // TO CHILD CLASSES
    protected abstract void stareAtDino(); // We should have some better ideas

}
