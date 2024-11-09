package repositories.interfaces;

import models.enums.ParkEvent;

import java.util.List;

public interface IParkEventRepository {

    // We are not allowing custom ParkEvent this time - It is restricted to what is already registered in DB.

    ParkEvent getParkEventById(int id);
    List<ParkEvent> getParkEvents();


}
