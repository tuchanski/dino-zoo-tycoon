package models;

import models.abstracts.Human;

public class Employee extends Human {

    // To implement the specific methods/atributes of an employee

    public Employee(String name, Integer age, String gender){
        super(name, age, gender);
    }

    @Override
    protected void stareAtDino() {
        System.out.println("Employee " + this.getName() + " is gazing at the dinosaurs they glimpsed.");
    }
    
}
