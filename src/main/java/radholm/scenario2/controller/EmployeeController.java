package radholm.scenario2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import radholm.scenario2.common.Role;
import radholm.scenario2.common.RoleType;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.service.EmployeeService;

import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(path = "/api/v1/")
public class EmployeeController implements Serializable {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "{roleType}")
    public List<Employee> getEmployees(@PathVariable("roleType") RoleType roleType,
                                       @RequestParam(required = false) Boolean getSelf) {
        return employeeService.getEmployees(roleType);
    }

    @PostMapping(value = {"{roleType}", "{roleType}/{superiorId}"})
    public void addEmployee(@PathVariable("roleType") RoleType roleType,
                            @PathVariable(value = "superiorId", required = false) Long superiorId,
                            @RequestBody Employee employee) {
        superiorId = superiorId == null ? 0L : superiorId;
        Employee emp = new Employee(employee.getFirstName(), employee.getLastName(), employee.getRank(), new Role(roleType));
        employeeService.addEmployee(emp, superiorId);
    }

    @DeleteMapping(path = "{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping(path = "{roleType}/{employeeId}")
    public void updateEmployee(@PathVariable("employeeId") Long employeeId,
                               @PathVariable("roleType") RoleType roleType,
                               @RequestBody Employee employee) {
        employeeService.updateEmployee(employeeId, employee.getFirstName(), employee.getLastName(), employee.getRank());
    }
}
