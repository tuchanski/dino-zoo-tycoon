package controllers;

import models.enums.ParkEvent;
import repositories.ParkEventRepositoryImpl;
import repositories.interfaces.IParkEventRepository;

import java.util.List;

public class ParkEventController {

    private IParkEventRepository parkEventRepository = new ParkEventRepositoryImpl();

    public ParkEvent getParkEventById(int id) {
        return parkEventRepository.getParkEventById(id);
    }

    public List<ParkEvent> getAllParkEvents() {
        return parkEventRepository.getParkEvents();
    }

}
