package models.enums;

public enum ParkEvent {
    DINO_FEEDING("Dinosaurs are being fed!"),
    DINO_PLAYTIME("Dinosaurs are playing with each other!"),
    DINO_ESCAPED("A dinosaur has escaped its enclosure!"),
    NEW_DINO_ARRIVAL("A new dinosaur has arrived at the park!"),
    DINO_SLEEPING("Dinosaurs are sleeping peacefully."),
    WEATHER_CHANGE("Weather has changed suddenly!"),
    DINO_SHOW("A dinosaur show is starting soon!"),
    VISITOR_COMPLAINT("A visitor has made a complaint!");

    private final String description;

    ParkEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
