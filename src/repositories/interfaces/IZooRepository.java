package repositories.interfaces;

import exceptions.EntityNotFoundException;
import models.Zoo;

import java.util.List;

public interface IZooRepository {
    void createZoo(String name, String location);
    List<Zoo> getAllZoos();
    Zoo getZooById(Long id);
    Zoo updateZooById(Long id, String newName, String newLocation) throws EntityNotFoundException;
    Zoo deleteZooById(Long id) throws EntityNotFoundException;
}
