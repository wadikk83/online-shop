package by.wadikk.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableWebSecurity

@Component
public class Runner {
    public static void main(String[] args) {

        SpringApplication.run(Runner.class, args);
    }

}
