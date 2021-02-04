package radholm.scenario2.service;

import radholm.scenario2.common.RoleType;
import radholm.scenario2.domain.Employee;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Interface that should be implemented by every service class
 */
public interface EmployeeService {

    List<Employee> getEmployees(RoleType roleType);

    void addEmployee(Employee employee, Long superiorId);

    void deleteEmployee(Long employeeId);

    @Transactional
    void updateEmployee(Long employeeId, Employee empUpdate, Long superiorId);
}
