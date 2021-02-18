package radholm.scenario2.configuration;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import radholm.scenario2.common.Role;
import radholm.scenario2.common.RoleType;
import radholm.scenario2.domain.Employee;
import radholm.scenario2.exception.AsyncExceptionHandler;
import radholm.scenario2.repository.EmployeeRepository;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Config class that contains Configuration Metadata and Dependency Injection
 */
@Configuration
@ComponentScan(basePackages = {"radholm.scenario2"})
@EnableAsync
public class EmployeeConfig extends AsyncConfigurerSupport {

    private final AsyncExceptionHandler asyncExceptionHandler;

    public EmployeeConfig(AsyncExceptionHandler asyncExceptionHandler) {
        this.asyncExceptionHandler = asyncExceptionHandler;
    }

    /**
     * Method that implements an asynchronous executor
     *
     * @return an async executor
     */
    @Override
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
        te.setCorePoolSize(4);
        te.setMaxPoolSize(8);
        te.setQueueCapacity(512);
        te.setThreadNamePrefix("Async-thread");
        te.initialize();
        return te;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return asyncExceptionHandler;
    }

    /**
     * Method to configure/seed database on create Remember that this data 'skips' the business logic
     * and relations
     *
     * @param repository DAO context
     * @return commandline args
     */
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee emp1 = new Employee("Elon", "Musk", 10, new Role(RoleType.CEO));
            Employee emp2 = new Employee("Karen", "Manager", 8, new Role(RoleType.MANAGER));
            Employee emp3 = new Employee("Kanye", "West", 1, new Role(RoleType.EMPLOYEE));

            emp1.setSubordinates(emp2);
            emp2.setManager(emp1);

            emp2.setSubordinates(emp3);
            emp3.setManager(emp2);

            repository.saveAll(List.of(emp1, emp2, emp3));
        };
    }
}
