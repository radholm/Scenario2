package radholm.scenario2.common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.repository.EmployeeRepository;

import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean

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
            //        List.of(emp1, emp2, emp3, emp4)
            //);
        };
    }
}
