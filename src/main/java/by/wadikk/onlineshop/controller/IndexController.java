package by.wadikk.onlineshop.controller;

import by.wadikk.onlineshop.entity.User;

import by.wadikk.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String login = ((User) principal).getLogin();
            model.addAttribute("login", login);
            model.addAttribute("user", userService.findByLogin(login));
        }
        return "index";
    }
}
