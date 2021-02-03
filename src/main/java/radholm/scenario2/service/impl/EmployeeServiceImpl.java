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

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees(RoleType roleType) {
        return switch (roleType) {
            case EMPLOYEE -> employeeRepository.findAllEmployees();
            case MANAGER -> employeeRepository.findAllManagers();
            case CEO -> employeeRepository.findCeo().stream().collect(Collectors.toList());
        };
    }

    //MAKE HANDLE CEO -> EMPLOYEE RELATIONSHIP
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
            employee.setManager(superior);
            superior.setSubordinates(employee);
            employeeRepository.save(superior);
        }
        employeeRepository.save(employee);
    }

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

    @Override
    @Transactional
    public void updateEmployee(Long employeeId, String firstName, String lastName, Integer rank) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId
                        + " does not exists"));
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
