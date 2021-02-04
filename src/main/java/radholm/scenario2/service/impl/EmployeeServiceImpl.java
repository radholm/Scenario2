package radholm.scenario2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import radholm.scenario2.common.RoleType;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.repository.EmployeeRepository;
import radholm.scenario2.service.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class that implements the service interface and business logic
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Gets/Fetches employees of certain role/roles
     *
     * @param roleType which roleType to fetch
     * @return a list of the employees belonging to the context (roleType)
     */
    @Override
    public List<Employee> getEmployees(RoleType roleType) {
        return switch (roleType) {
            case EMPLOYEE -> employeeRepository.findAllEmployees();
            case MANAGER -> employeeRepository.findAllManagers();
            case CEO -> employeeRepository.findCeo().stream().collect(Collectors.toList());
        };
    }

    /**
     * Adds/Creates an employee with implemented business logic
     * - A 'regular' employee must declare its superiorId
     * - A 'regular' employee cannot be a subordinate of a CEO
     * - Cannot create a CEO, if a CEO already exists
     *
     * @param superiorId an optional parameter, used to create 'regular' employees
     * @param employee   the body/data that creates the employee
     */
    @Override
    public void addEmployee(Employee employee, Long superiorId) {
        Optional<Employee> optionalSuperior = employeeRepository.findManagerById(superiorId);
        if (employee.getIsCeo()) {
            employeeRepository.findCeo()
                    .ifPresent(ceo -> {
                        throw new IllegalStateException("Cannot assign CEO since id " + ceo.getId()
                                + " is already a CEO");
                    });
        } else if (!(employee.getRank() >= 1 && 10 >= employee.getRank())) {
            throw new IllegalStateException("Cannot create employee since the rank " + employee.getRank()
                    + "is not in the range 1<=rank<=10");
        } else if (!employee.getIsManager() && (superiorId.equals(0L) || optionalSuperior.isEmpty())) {
            throw new IllegalStateException("Cannot create employee, specify a valid manager id for the employee");
        } else if (optionalSuperior.isPresent() && !employee.getIsCeo()) {
            Employee superior = optionalSuperior.get();
            if ((!employee.getIsManager() && !employee.getIsCeo()) && superior.getIsCeo()) {
                throw new IllegalStateException("A 'regular' employee cannot be a subordinate of a CEO");
            }
            employee.setManager(superior);
            superior.setSubordinates(employee);
            employeeRepository.save(superior);
        }
        employeeRepository.save(employee);
    }

    /**
     * Deletes an employee
     * - An employee as manager/CEO cannot be deleted if it has subordinates
     *
     * @param employeeId the id of the employee to delete
     */
    @Override
    public void deleteEmployee(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        List<Employee> subordinates = employeeRepository.findAllSubordinates(employeeId);
        if (!exists) {
            throw new IllegalStateException("Employee with id " + employeeId
                    + " does not exists");
        } else if (!subordinates.isEmpty()) {
            throw new IllegalStateException("Employee with id " + employeeId
                    + " has subordinates, cannot delete");
        }
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Updates an employee
     * - As "Transactional", the service provides a "managed state",
     * where persisted entities can be manipulated directly
     *
     * @param employeeId the id of the employee to update
     * @param superiorId an optional parameter, used to update the subordinates manager
     * @param empUpdate  the body/data that updates the employee
     */
    @Override
    @Transactional
    public void updateEmployee(Long employeeId, Employee empUpdate, Long superiorId) {
        if (empUpdate.getIsCeo()) {
            employeeRepository.findCeo()
                    .ifPresent(ceo -> {
                        throw new IllegalStateException("Cannot assign CEO since id " + ceo.getId()
                                + " is already a CEO");
                    });
        }
        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId
                        + " does not exists"));
        Optional<Employee> optionalSuperior = employeeRepository.findManagerById(superiorId);
        if (!empUpdate.getIsManager() && (superiorId.equals(0L) || optionalSuperior.isEmpty())) {
            throw new IllegalStateException("Cannot update employee, specify a valid manager id for the employee");
        }
        if (optionalSuperior.isPresent() && !emp.getIsCeo()) {
            Employee superior = optionalSuperior.get();
            if ((!empUpdate.getIsManager() && !empUpdate.getIsCeo()) && superior.getIsCeo()) {
                throw new IllegalStateException("A 'regular' employee cannot be a subordinate of a CEO");
            }
            emp.setManager(superior);
            superior.setSubordinates(emp);
        }
        if (empUpdate.getFirstName() != null
                && empUpdate.getFirstName().length() > 0
                && !Objects.equals(emp.getFirstName(), empUpdate.getFirstName())) {
            emp.setFirstName(empUpdate.getFirstName());
        }
        if (empUpdate.getLastName() != null
                && empUpdate.getLastName().length() > 0
                && !Objects.equals(emp.getLastName(), empUpdate.getLastName())) {
            emp.setLastName(empUpdate.getLastName());
        }
        if (empUpdate.getRank() >= 1
                && 10 >= empUpdate.getRank()
                && !emp.getRank().equals(empUpdate.getRank())) {
            emp.setRank(empUpdate.getRank());
            emp.setSalary(empUpdate.getSalary());
        }
        if (!Objects.equals(emp.getIsManager(), empUpdate.getIsManager())) {
            emp.setIsManager(empUpdate.getIsManager());
            emp.setSalary(empUpdate.getSalary());
        } else if (!Objects.equals(emp.getIsCeo(), empUpdate.getIsCeo())) {
            emp.setIsCeo(empUpdate.getIsCeo());
            emp.setSalary(empUpdate.getSalary());
        }
    }
}