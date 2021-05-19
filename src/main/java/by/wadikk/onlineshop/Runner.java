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
    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    /*@Autowired
    private UserService userService;*/


    @Override
    public void afterPropertiesSet() throws Exception {
        /*userService.createUser("admin","admin","111@tut.by", Role.ADMIN);
        userService.createUser("user","user","222@tut.by", Role.USER);*/
    }
}
