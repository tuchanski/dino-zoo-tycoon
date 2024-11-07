package models;

import models.abstracts.Human;

public class Visitor extends Human {

    // To implement the specific methods/atributes of a visitor

    public Visitor(String name) {
        super(name);
    }

    @Override
    protected void stareAtDino() {
        System.out.println("Visitor " + this.getName() + " is gazing at the dinosaurs they glimpsed.");
    }

}
