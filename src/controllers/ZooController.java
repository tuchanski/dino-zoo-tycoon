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

    public void createZoo(String name){
        zooRepository.createZoo(name);
    }

    public List<Zoo> getAllZoos(){
        return zooRepository.getAllZoos();
    }

    public Zoo getZooById(int id){
        return zooRepository.getZooById((long) id);
    }

    public Zoo getZooByUser() {
        return zooRepository.getZooByUser();
    }

    public Zoo updateZooById(int id, String newName) {

        Zoo zoo = null;

        try {
            zoo = zooRepository.updateZooById((long) id, newName);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return zoo;

    }

    public Zoo deleteZooById(int id) {
        Zoo zoo = null;

        try {
            zoo = zooRepository.deleteZooById((long) id);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return zoo;
    }

    public void addCash(int id, int amount) {

        try {
            zooRepository.addCash((long) id, amount);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void removeCash(int id, int amount) {

        try {
            zooRepository.removeCash((long) id, amount);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
