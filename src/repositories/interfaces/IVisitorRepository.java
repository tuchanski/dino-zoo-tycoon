package repositories.interfaces;

import exceptions.EntityNotFoundException;
import models.Visitor;

import java.util.List;

public interface IVisitorRepository {

    void createGenericVisitor(); // Random Names
    List<Visitor> getVisitors();
    Visitor getVisitorById(Long visitorId);
    Visitor deleteVisitorById(Long visitorId) throws EntityNotFoundException;
    String getDailyTask(Long visitorId);

}
