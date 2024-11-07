package repositories.interfaces;

import exceptions.EntityNotFoundException;
import models.Visitor;

import java.util.List;

public interface IEmployeeRepository {
    void createGenericEmployee(); // Random Names
    List<Visitor> getEmployees();
    Visitor getEmployeeById(Long employeeId);
    Visitor deleteEmployeeById(Long employeeId) throws EntityNotFoundException;
}
