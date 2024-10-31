// REVISAR

package models;

public abstract class Dinosaur {
    
    private String name; 
    private int age;
    private double weight;

    public Dinosaur(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    // Método abstrato para obter a dieta do dinossauro (a ser implementado nas subclasses)
    public abstract String getDiet();

    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Weight: " + weight + " kg");
    }
}
