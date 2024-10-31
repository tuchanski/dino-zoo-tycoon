package models;

import models.enums.ParkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Zoo {

    private static Long idCounter = 0L;

    private Long id;
    private Inventory inventory;
    private List<ParkEvent> eventHistory;

    public Zoo(Inventory inventory) {
        this.id = ++idCounter;
        this.inventory = inventory;
        this.eventHistory = new ArrayList<>();
    }

    public void triggerRandomEvent() {
        ParkEvent randomEvent = getRandomParkEvent();
        eventHistory.add(randomEvent);
        System.out.println("Event occurred: " + randomEvent.getDescription());
    }

    private ParkEvent getRandomParkEvent() {
        ParkEvent[] events = ParkEvent.values();
        Random random = new Random();
        return events[random.nextInt(events.length)];
    }

    public void displayEventHistory() {
        System.out.println("Event History:");
        eventHistory.stream().forEach(event -> System.out.println(event.getDescription()));
    }

    public Long getId() {
        return id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Zoo [id=" + id + ", inventory=" + inventory + ", eventHistory=" + eventHistory + "]";
    }

}
