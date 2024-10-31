package models;

import java.util.ArrayList;
import java.util.List;

public class Enclosure {

    private String name;
    private Double area;
    private List<Dinosaur> dinosaurs;
    private List<Food> availableFoods;

    public Enclosure(String name, double area) {
        this.name = name;
        this.area = area;
        this.dinosaurs = new ArrayList<>();
        this.availableFoods = new ArrayList<>();
    }

    public void addDinosaur(Dinosaur dinosaur) {
        this.dinosaurs.add(dinosaur);
    }

    public void addFood(Food food) {
        this.availableFoods.add(food);
    }

    public void showEnclosureInfo() {
        System.out.println("Enclosure Name: " + name);
        System.out.println("Area: " + area + " square meters");

        if (!dinosaurs.isEmpty()) {
            dinosaurs.forEach(Dinosaur::showInfo);
        }
        if (!availableFoods.isEmpty()) {
            availableFoods.forEach(food -> System.out.println(food.toString()));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public List<Food> getAvailableFoods() {
        return availableFoods;
    }
}
