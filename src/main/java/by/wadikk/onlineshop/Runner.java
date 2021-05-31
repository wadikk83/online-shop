package by.wadikk.onlineshop;

import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableWebSecurity

@Component
public class Runner implements InitializingBean {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userService.createActiveUser("admin", "by.wadikk.onlineshop@gmail.com", "admin", Role.ADMIN);
        //userService.createUser("admin", "by.wadikk.onlineshop@gmail.com", "admin", Role.ADMIN);
    }
}
