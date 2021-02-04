package radholm.scenario2.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.repository.EmployeeRepository;

@Configuration
@ComponentScan(basePackageClasses = Employee.class)
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            //Employee emp1 = new Employee("Fredrik", "Radholm", 1, new Role(RoleType.EMPLOYEE));
            //Employee emp2 = new Employee("Mats", "Radholm", 1, new Role(RoleType.MANAGER));
            //Employee emp3 = new Employee("Carina", "Radholm", 1, new Role(RoleType.CEO));
            //Employee emp4 = new Employee("Johanna", "Carlsson", 1, new Role(RoleType.EMPLOYEE));

            //emp1.setManager(emp3);
            //emp3.setSubordinates(emp1);
            //emp3.setIsCeo(true);
            //emp4.setManager(emp1);
            //emp3.setSubordinates(emp4);

            //repository.saveAll(
            //        List.of(emp1)
            //);
        };
    }
}
