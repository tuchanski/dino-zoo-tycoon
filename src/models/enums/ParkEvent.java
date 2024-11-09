package models.enums;

public enum ParkEvent {
    DINO_PLAYTIME( "Dinosaurs are playing with each other!"),
    DINO_ESCAPED( "A dinosaur has escaped its enclosure!"),
    DINO_SLEEPING( "Dinosaurs are sleeping peacefully."),
    WEATHER_CHANGE( "Weather has changed suddenly!"),
    DINO_SHOW("A dinosaur show is starting soon!"),
    VISITOR_COMPLAINT( "A visitor has made a complaint!");

    private final String description;

    ParkEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
