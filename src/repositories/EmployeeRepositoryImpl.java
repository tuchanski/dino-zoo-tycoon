package repositories;

import exceptions.EntityNotFoundException;
import models.DB;
import models.Employee;
import models.Visitor;
import models.Zoo;
import repositories.interfaces.IEmployeeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EmployeeRepositoryImpl implements IEmployeeRepository {

    private final Zoo zoo;

    private Connection getConnection() throws SQLException {
        return DB.connect();
    }

    private List<String> names = Arrays.asList("Ada", "Turing", "Linus", "Pixel",
            "Java", "Ruby", "Dash", "Echo", "Bit", "Ada", "Logic", "Milo",
            "Bug", "Zara", "Kernel", "Byte", "Neo", "Clover");

    public EmployeeRepositoryImpl(Zoo zoo) {
        this.zoo = zoo;
    }

    @Override
    public void createGenericEmployee(){

        String name = getRandomName();
        String dailyTask = new Employee(null, name, zoo.getZooId()).getDailyTask();
        String createGenericEmployeeQuery = "INSERT INTO employee (name, zoo_id, daily_task) VALUES (?, ?, ?)";

        try {

            PreparedStatement createGenericEmployeePs = getConnection().prepareStatement(createGenericEmployeeQuery);
            createGenericEmployeePs.setString(1, name);
            createGenericEmployeePs.setLong(2, zoo.getZooId());
            createGenericEmployeePs.setString(3, dailyTask);
            createGenericEmployeePs.execute();
            createGenericEmployeePs.close();

            System.out.println("Employee " + name + " has been created successfully to zoo: " + zoo.getName());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public String getDailyTask(Long employeeId) {
        String dailyTask = null;
        String getDailyTaskQuery = "SELECT * FROM employee WHERE employee_id = ? AND zoo_id = ?";

        try {
            PreparedStatement getDailyTaskPs = getConnection().prepareStatement(getDailyTaskQuery);
            getDailyTaskPs.setLong(1, employeeId);
            getDailyTaskPs.setLong(2, zoo.getZooId());
            ResultSet rs = getDailyTaskPs.executeQuery();

            if (rs.next()) {
                dailyTask = rs.getString("daily_task");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return dailyTask;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        String getEmployeesQuery = "SELECT * FROM employee WHERE zoo_id = ?";

        try {

            PreparedStatement getEmployeesPs = getConnection().prepareStatement(getEmployeesQuery);
            getEmployeesPs.setLong(1, zoo.getZooId());
            ResultSet rs = getEmployeesPs.executeQuery();

            while (rs.next()) {
                Long employeeId = rs.getLong("employee_id");
                String name = rs.getString("name");
                Long zooId = rs.getLong("zoo_id");
                employees.add(new Employee(employeeId, name, zooId));
            }

            getEmployeesPs.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return employees;
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        Employee employee = null;

        String getEmployeeQuery = "SELECT * FROM employee WHERE employee_id = ? AND zoo_id = ?";

        try {

            PreparedStatement getEmployeePs = getConnection().prepareStatement(getEmployeeQuery);
            getEmployeePs.setLong(1, employeeId);
            getEmployeePs.setLong(2, zoo.getZooId());
            ResultSet rs = getEmployeePs.executeQuery();

            if (rs.next()) {
                String employeeName = rs.getString("name");
                employee = new Employee(employeeId, employeeName, zoo.getZooId());
            }

            rs.close();
            getEmployeePs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return employee;
    }

    @Override
    public Employee deleteEmployeeById(Long employeeId) throws EntityNotFoundException {

        Employee employee = getEmployeeById(employeeId);

        if (employee == null) {
            throw new EntityNotFoundException("Employee with id " + employeeId + " not found");
        }

        String deleteEmployeeQuery = "DELETE FROM employee WHERE employee_id = ? AND zoo_id = ?";

        try {
            PreparedStatement deleteEmployeePs = getConnection().prepareStatement(deleteEmployeeQuery);
            deleteEmployeePs.setLong(1, employeeId);
            deleteEmployeePs.setLong(2, zoo.getZooId());
            deleteEmployeePs.execute();
            deleteEmployeePs.close();

            System.out.println("Employee with ID" + employee.getId() + " has been deleted successfully to zoo: " + zoo.getName());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return employee;

    }

    private String getRandomName() {
        Random random = new Random();
        return names.get(random.nextInt(names.size()));
    }


}
