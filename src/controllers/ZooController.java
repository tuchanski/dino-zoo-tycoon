package controllers;

import exceptions.EntityNotFoundException;
import exceptions.NotEnoughMoneyException;
import models.User;
import models.Visitor;
import models.Zoo;
import repositories.ZooRepositoryImpl;
import repositories.interfaces.IZooRepository;

import javax.swing.*;
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

    public String getZooName() {
        Zoo zoo = getZooByUser();
        if (zoo != null) {
            return zoo.getName();
        }
        return "Zoo";
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

    public void addCash(long id, int amount) {

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

    public int getCurrentCash(long id) {
        int cash = 0;
        try {
            cash = zooRepository.getCurrentCash(id);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return cash;
    }

    public void contractEmployee(int id) {
        try {
            zooRepository.contractNewEmployee((long) id);
        } catch (EntityNotFoundException | NotEnoughMoneyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Swing-views utility shortcut
    public void addVisitor(Zoo zoo, JTextArea logTextArea) {
        VisitorController visitorController = new VisitorController(zoo);
        visitorController.createVisitor();
        List<Visitor> visitors = visitorController.getVisitors();
        System.out.println("Visitor " + visitors.getLast() + " arrived.");

        String visitorName = visitors.getLast().getName();

        addCash(zoo.getZooId(), 50);
        System.out.println("Cash: +50 | Total: " + (zoo.getCash() + 50));

        String visitorLog = "Visitor " + visitorName + " arrived at zoo (+ $50)";
        logTextArea.append(visitorLog + "\n");
        logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
    }

}
