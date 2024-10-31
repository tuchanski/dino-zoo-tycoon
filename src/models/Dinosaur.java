package models;

import models.enums.DinosaurType;

public class Dinosaur {
    private static Long idCounter = 0L;

    private Long id;
    private String name;
    private DinosaurType type;
    private Integer age;

    public Dinosaur(String name, DinosaurType type, Integer age) {
        this.id = ++idCounter;
        this.name = name;
        this.type = type;
        this.age = age;
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

    public DinosaurType getType() {
        return type;
    }

    public void setType(DinosaurType type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Dinosaur ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type.getName());
        System.out.println("Age: " + age + " years");
    }
    
}
