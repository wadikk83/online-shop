package by.wadikk.onlineshop.controller;

import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.service.UserService;
import by.wadikk.onlineshop.service.impl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;


    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("usernameExists", model.asMap().get("usernameExists"));
        model.addAttribute("emailExists", model.asMap().get("emailExists"));
        return "login";
    }

    @PostMapping("/new-user")
    public String newUserPost(@Valid @ModelAttribute("user") User user, BindingResult bindingResults,
                              @ModelAttribute("new-password") String password,
                              RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute("email", user.getEmail());
        model.addAttribute("login", user.getLogin());
        boolean invalidFields = false;
        if (bindingResults.hasErrors()) {
            return "redirect:/login";
        }
        if (userService.findByLogin(user.getLogin()) != null) {
            redirectAttributes.addFlashAttribute("usernameExists", true);
            invalidFields = true;
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("emailExists", true);
            invalidFields = true;
        }
        if (invalidFields) {
            return "redirect:/login";
        }
        user = userService.createUser(user.getLogin(), password, user.getEmail(), Role.USER);

        userSecurityService.authenticateUser(user.getUsername());
        return "redirect:/my-profile";
    }


}
