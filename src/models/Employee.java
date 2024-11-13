package models;

import models.abstracts.Human;

import java.util.List;
import java.util.Random;

public class Employee extends Human {

    public Employee(Long id, String name, Long zooId) {
        super(id, name, zooId);
    }

    public Employee(String name){
        super(name);
    }

    @Override
    public String getRole() {
        return "Employee";
    }

    @Override
    public String getDailyTask() {
        Random random = new Random();
        List<String> tasks = List.of("Clean enclosure", "Guide visitors", "Feed dinosaurs");
        return tasks.get(random.nextInt(tasks.size()));
    }
    
}
