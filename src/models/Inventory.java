package models;

import java.util.ArrayList;
import java.util.List;

import models.abstracts.Human;

public class Inventory {

    private List<Human> zooHumanPopulation;
    private List<Enclosure> zooEnclosures;

    public Inventory(){
        this.zooEnclosures = new ArrayList<>();
        this.zooHumanPopulation = new ArrayList<>();
    }

    public List<Human> getZooHumanPopulation() {
        return zooHumanPopulation;
    }

    public List<Enclosure> getZooEnclosures() {
        return zooEnclosures;
    }

    
}
