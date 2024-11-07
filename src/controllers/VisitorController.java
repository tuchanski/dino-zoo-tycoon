package controllers;

import exceptions.EntityNotFoundException;
import models.Visitor;
import models.Zoo;
import repositories.VisitorRepositoryImpl;
import repositories.interfaces.IVisitorRepository;

import java.util.List;

public class VisitorController {

    private final IVisitorRepository visitorRepository;

    public VisitorController(Zoo zoo) {
        visitorRepository = new VisitorRepositoryImpl(zoo);
    }

    public void createVisitor() {
        visitorRepository.createGenericVisitor();
    }

    public List<Visitor> getVisitors() {
        return visitorRepository.getVisitors();
    }

    public Visitor getVisitorById(int id) {
        return visitorRepository.getVisitorById((long) id);
    }

    public Visitor deleteVisitorById(int id) throws EntityNotFoundException {
        return visitorRepository.deleteVisitorById((long) id);
    }
    

}
