package models;

import models.abstracts.Human;

import java.util.List;
import java.util.Random;

public class Visitor extends Human {

    public Visitor(Long id, String name, Long zooId){
        super(id, name, zooId);
    }

    @Override
    public String getRole() {
        return "Visitor";
    }

    @Override
    public String getDailyTask() {
        Random random = new Random();
        List<String> tasks = List.of("Follow the dinosaur footprints trail",
                "Feed a friendly Brontosaurus",
                    "Participate in a dinosaur fossil excavation");
        return tasks.get(random.nextInt(tasks.size()));
    }

}
