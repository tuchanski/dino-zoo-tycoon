package controllers;

import exceptions.EntityNotFoundException;
import models.Employee;
import models.Zoo;
import repositories.EmployeeRepositoryImpl;
import repositories.interfaces.IEmployeeRepository;

import java.util.List;

public class EmployeeController {


    private final IEmployeeRepository employeeRepository;

    public EmployeeController(Zoo zoo) {
        employeeRepository = new EmployeeRepositoryImpl(zoo);
    }

    public void createEmployee() {
        employeeRepository.createGenericEmployee();
    }

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById((long) id);
    }

    public Employee deleteEmployeeById(int id) throws EntityNotFoundException {
        return employeeRepository.deleteEmployeeById((long) id);
    }

}
