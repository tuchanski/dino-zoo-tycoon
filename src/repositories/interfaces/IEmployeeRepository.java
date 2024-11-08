package repositories.interfaces;

import exceptions.EntityNotFoundException;
import models.Employee;
import models.Visitor;

import java.util.List;

public interface IEmployeeRepository {
    void createGenericEmployee(); // Random Names
    List<Employee> getEmployees();
    Employee getEmployeeById(Long employeeId);
    Employee deleteEmployeeById(Long employeeId) throws EntityNotFoundException;
}
