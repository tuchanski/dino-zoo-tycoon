package repositories.interfaces;

import exceptions.EntityNotFoundException;
import models.Zoo;

import java.util.List;

public interface IZooRepository {
    void createZoo(String name);
    List<Zoo> getAllZoos();
    Zoo getZooById(Long id);
    Zoo getZooByUser();
    Zoo updateZooById(Long id, String newName) throws EntityNotFoundException;
    Zoo deleteZooById(Long id) throws EntityNotFoundException;
    void addCash(Long id, int amount) throws EntityNotFoundException;
    void removeCash(Long id, int amount) throws EntityNotFoundException;
}
