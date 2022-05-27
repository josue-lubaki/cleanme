package ca.josue.cleanme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CleanMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleanMeApplication.class, args);
    }

}
