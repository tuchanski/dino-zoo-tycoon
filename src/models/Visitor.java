package models;

import models.abstracts.Human;

public class Visitor extends Human {

    // To implement the specific methods/atributes of a visitor

    private Ticket ticket;

    public Visitor(String name, Integer age, String gender, Ticket ticket) {
        super(name, age, gender);
        this.ticket = ticket;
    }

    @Override
    protected void stareAtDino() {
        System.out.println("Visitor " + this.getName() + " is gazing at the dinosaurs they glimpsed.");
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    
}
