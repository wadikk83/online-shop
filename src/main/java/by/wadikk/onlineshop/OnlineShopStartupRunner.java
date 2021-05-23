package by.wadikk.onlineshop;

import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OnlineShopStartupRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.createUser("admin", "admin", "1111@tut.by", Role.ADMIN);
    }
}
