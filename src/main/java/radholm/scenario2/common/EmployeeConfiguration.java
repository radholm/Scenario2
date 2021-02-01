package radholm.scenario2.common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.repository.EmployeeRepository;

import java.util.List;

@Configuration
public class EmployeeConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee emp1 = new Employee("Fredrik", "Radholm", 2);
            Employee emp2 = new Employee("Mats", "Radholm", 10);
            Employee emp3 = new Employee("Carina", "Radholm", 5);
            Employee emp4 = new Employee("Johanna", "Carlsson", 3);

            emp2.setManager(emp3);
            emp1.setManager(emp3);
            emp3.setSubordinates(emp2);
            emp3.setSubordinates(emp1);
            emp3.setIsCEO(true);
            emp4.setManager(emp1);
            emp3.setSubordinates(emp4);

            repository.saveAll(
                    List.of(emp1, emp2, emp3, emp4)
            );
        };
    }
}
