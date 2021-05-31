package by.wadikk.onlineshop.controller;

import by.wadikk.onlineshop.entity.ConfirmationToken;
import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.repository.ConfirmationTokenRepository;
import by.wadikk.onlineshop.service.UserService;
import by.wadikk.onlineshop.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "addUser";
    }

    @PostMapping("/add")
    public String addUserPost(@ModelAttribute("user") User user,
                              @RequestParam boolean isEnabled,
                              @RequestParam boolean isBlackList) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());

        String password = user.getPassword();
        newUser.setPassword(SecurityUtility.passwordEncoder().encode(password));

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEnabled(isEnabled);
        newUser.setBlackList(isBlackList);
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());

        userService.save(newUser);
        return "redirect:user-list";
    }

    @GetMapping("/user-list")
    public String userList(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id);

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "editUser";
    }

    @PostMapping("/edit")
    public String editUserPost(@ModelAttribute("user") User user,
                               @RequestParam boolean isEnabled,
                               @RequestParam boolean isBlackList) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());

        String newPassword = user.getPassword();
        String oldPassword = userService.findById(user.getId()).getPassword();

        if (!newPassword.equals(oldPassword)) {
            newUser.setPassword(SecurityUtility.passwordEncoder().encode(newPassword));
        } else newUser.setPassword(oldPassword);

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEnabled(isEnabled);
        newUser.setBlackList(isBlackList);
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());

        newUser.setId(user.getId());

        userService.save(newUser);

        return "redirect:user-list";
    }

    @GetMapping("/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        User user = userService.findById(id);
        ConfirmationToken confirmationToken = confirmationTokenRepository.findConfirmationTokenByUser(user);
        if (confirmationToken != null) {
            confirmationTokenRepository.delete(confirmationToken);
        }

        userService.deleteUserById(id);
        return "redirect:user-list";
    }
}
