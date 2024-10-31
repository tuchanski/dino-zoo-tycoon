// REVISAR

package models;

import java.util.ArrayList;
import java.util.List;

public class Enclosure {

    private String name;
    private double area;  
    private List<Dinosaur> dinosaurs; 

    public Enclosure(String name, double area) {
        this.name = name;
        this.area = area;
        this.dinosaurs = new ArrayList<>();
    }

    public void addDinosaur(Dinosaur dinosaur) {
        this.dinosaurs.add(dinosaur);
    }

    public void showEnclosureInfo() {
        System.out.println("Enclosure Name: " + name);
        System.out.println("Area: " + area + " square meters");

        if (!dinosaurs.isEmpty()) {
            dinosaurs.stream().iterator().forEachRemaining(dinosaur -> dinosaur.showInfo());
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
  
}
