package controllers;

import exceptions.EntityNotFoundException;
import models.User;
import models.Zoo;
import repositories.ZooRepositoryImpl;
import repositories.interfaces.IZooRepository;

import java.util.List;

public class ZooController {

    private final IZooRepository zooRepository;

    public ZooController(User user) {
        this.zooRepository = new ZooRepositoryImpl(user);
    }

    public void createZoo(String name, String location){
        zooRepository.createZoo(name, location);
    }

    public List<Zoo> getAllZoos(){
        return zooRepository.getAllZoos();
    }

    public Zoo getZooById(int id){
        return zooRepository.getZooById((long) id);
    }

    public Zoo updateZooById(int id, String newName, String newLocation) throws EntityNotFoundException {
        return zooRepository.updateZooById((long) id, newName, newLocation);
    }

    public Zoo deleteZooById(int id) throws EntityNotFoundException {
        return zooRepository.deleteZooById((long) id);
    }

}