package radholm.scenario2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import radholm.scenario2.common.Role;
import radholm.scenario2.common.RoleType;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.service.EmployeeService;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * Controller that processes REST API requests, such as GET, POST, PUT and DELETE
 */
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(path = "/api/v1/")
public class EmployeeController implements Serializable {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Gets/Fetches employees of certain role/roles
     *
     * @param roleType which roleType to fetch
     * @return a list of the employees belonging to the context (roleType)
     */
    @GetMapping(path = "{roleType}")
    public List<Employee> getEmployees(@PathVariable("roleType") RoleType roleType) {
        return employeeService.getEmployees(roleType);
    }

    /**
     * Gets/Fetches employees of certain role/roles
     *
     * @param employeeId which employee to get by id
     * @return employee object
     */
    @GetMapping(path = "/employee/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    /**
     * Adds/Creates an employee
     *
     * @param roleType   states the wanted role in the employee context
     * @param superiorId an optional parameter, used to create 'regular' employees
     * @param employee   the body/data that creates the employee
     */
    @PostMapping(value = {"{roleType}", "{roleType}/{superiorId}"})
    public void addEmployee(@PathVariable("roleType") RoleType roleType,
                            @PathVariable(value = "superiorId", required = false) Long superiorId,
                            @RequestBody @Valid Employee employee,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError f : bindingResult.getFieldErrors()) {
                throw new IllegalStateException(f.getField() + ": " + f.getDefaultMessage());
            }
        } else {
            superiorId = superiorId == null ? 0L : superiorId;
            Employee emp = new Employee(employee.getFirstName(), employee.getLastName(), employee.getRank(), new Role(roleType));
            employeeService.addEmployee(emp, superiorId);
        }
    }

    /**
     * Deletes an employee
     *
     * @param employeeId the id of the employee to delete
     */
    @DeleteMapping(path = "{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    /**
     * Updates an employee
     *
     * @param employeeId the id of the employee to update
     * @param roleType   states the wanted/current role in the employee context
     * @param superiorId an optional parameter, used to update the subordinates manager
     * @param employee   the body/data that updates the employee
     */
    @PutMapping(value = {"{employeeId}/{roleType}", "{employeeId}/{roleType}/{superiorId}"})
    public void updateEmployee(@PathVariable("employeeId") Long employeeId,
                               @PathVariable("roleType") RoleType roleType,
                               @PathVariable(value = "superiorId", required = false) Long superiorId,
                               @RequestBody @Valid Employee employee,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError f : bindingResult.getFieldErrors()) {
                throw new IllegalStateException(f.getField() + ": " + f.getDefaultMessage());
            }
        } else {
            superiorId = superiorId == null ? 0L : superiorId;
            Employee emp = new Employee(employee.getFirstName(), employee.getLastName(), employee.getRank(), new Role(roleType));
            employeeService.updateEmployee(employeeId, emp, superiorId);
        }
    }
}
