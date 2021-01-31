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

            repository.saveAll(
                    List.of(emp1, emp2)
            );
        };
    }
}
