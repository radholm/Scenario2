package radholm.scenario2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Scenario2Application {

    public static void main(String[] args) {
        SpringApplication.run(Scenario2Application.class, args);
    }

}
