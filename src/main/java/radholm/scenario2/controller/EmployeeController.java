package radholm.scenario2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import radholm.scenario2.common.Role;
import radholm.scenario2.common.RoleType;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.service.EmployeeService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(path = "/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/managers")
    public List<Employee> getAllManagers(@RequestParam(required = false) Boolean getSelf) {
        if (getSelf != null && getSelf) {
            return employeeService.getAllManagers();
        } else if (getSelf != null) {
            //return all but self
        }
        return employeeService.getAllManagers();
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(new Employee(employee.getFirstName(), employee.getLastName(), employee.getRank(), new Role(RoleType.EMPLOYEE)));
    }

    @DeleteMapping(path = "{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping(path = "{employeeId}")
    public void updateEmployee(@PathVariable("employeeId") Long employeeId,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) Integer rank) {
        employeeService.updateEmployee(employeeId, firstName, lastName, rank);
    }
}
