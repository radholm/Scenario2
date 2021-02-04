package radholm.scenario2.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import radholm.scenario2.common.Role;
import radholm.scenario2.common.RoleType;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.repository.EmployeeRepository;

import java.util.List;

/**
 * Config class that contains Configuration Metadata and supports Dependency Injection
 */
@Configuration
@ComponentScan(basePackages = {"radholm.scenario2"})
public class EmployeeConfig {

    /**
     * Class to configure/seed database on create
     *
     * @param repository DAO context
     * @return commandline args
     */
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee emp1 = new Employee("Fredrik", "Radholm", 7, new Role(RoleType.CEO));

            repository.saveAll(
                    List.of(emp1)
            );
        };
    }
}
