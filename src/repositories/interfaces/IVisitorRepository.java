package repositories.interfaces;

import models.Visitor;

import java.util.List;

public interface IVisitorRepository {

    void createGenericVisitor(); // Random Names
    List<Visitor> getVisitors();
    Visitor getVisitor(Long visitorId);
    Visitor deleteVisitor(Long visitorId);

}
