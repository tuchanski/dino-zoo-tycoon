package repositories.interfaces;

import models.Visitor;

import java.util.List;

public interface IVisitorRepository {

    void createGenericVisitor(); // Random Names
    void createVisitor(String name);
    List<Visitor> getVisitors(Long zooId);
    Visitor getVisitor(Long visitorId);
    Visitor deleteVisitor(Long visitorId);

}
