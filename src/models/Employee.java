package models;

import models.abstracts.Human;

public class Employee extends Human {

    // To implement the specific methods/atributes of an employee

    public Employee(Long id, String name, Long zooId) {
        super(id, name, zooId);
    }

    public Employee(String name){
        super(name);
    }

    @Override
    protected void stareAtDino() {
        System.out.println("Employee " + this.getName() + " is gazing at the dinosaurs they glimpsed.");
    }
    
}
