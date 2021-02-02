package radholm.scenario2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getAllManagers() {
        return employeeRepository.findAllManagers();
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        List<Employee> subordinates = employeeRepository.findAllSubordinates(employeeId);
        if (!exists) {
            throw new IllegalStateException("Employee with id " + employeeId + " does not exists");
        } else if (!subordinates.isEmpty()) {
            throw new IllegalStateException("Employee with id " + employeeId + " has subordinates, cannot delete");
        }
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(Long employeeId, String firstName, String lastName, Integer rank) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId + " does not exists"));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(employee.getFirstName(), firstName)) {
            employee.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(employee.getLastName(), lastName)) {
            employee.setLastName(lastName);
        }
        if (rank >= 1 && 10 >= rank && !employee.getRank().equals(rank)) {
            employee.setRank(rank);
        }
    }
}
