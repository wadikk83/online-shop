package by.wadikk.onlineshop.controller;

import java.security.Principal;
import java.util.List;

import by.wadikk.onlineshop.entity.Order;
import by.wadikk.onlineshop.entity.Role;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.service.OrderService;
import by.wadikk.onlineshop.service.UserService;
import by.wadikk.onlineshop.service.impl.UserSecurityService;
import by.wadikk.onlineshop.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserSecurityService userSecurityService;

    @PostMapping
    public String loginPost(ModelMap model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            model.addAttribute("login", ((org.springframework.security.core.userdetails.User) principal).getUsername());
        } else {
            return "redirect:/login";
        }
        return "redirect:/index";
    }


    @GetMapping("/login")
    public String loginGet(Model model) {
        model.addAttribute("usernameExists", model.asMap().get("usernameExists"));
        model.addAttribute("emailExists", model.asMap().get("emailExists"));

        return "login";
    }

    @RequestMapping("/my-profile")
    public String myProfile(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "myProfile";
    }

    @GetMapping("/my-orders")
    public String myOrders(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        List<Order> orders = orderService.findByUser(user);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/new-user")
    public String newUserPost(@Valid @ModelAttribute("user") User user, BindingResult bindingResults,
                              @ModelAttribute("new-password") String password,
                              RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getUsername());
        boolean invalidFields = false;
        if (bindingResults.hasErrors()) {
            return "redirect:/login";
        }
        if (userService.findByUsername(user.getUsername()) != null) {
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
        user = userService.createUser(user.getUsername(), user.getEmail(), password, Role.USER);
        userSecurityService.authenticateUser(user.getUsername());
        return "redirect:/my-profile";
    }

    @RequestMapping(value = "/update-user-info", method = RequestMethod.POST)
    public String updateUserInfo(@ModelAttribute("user") User user,
                                 @RequestParam("newPassword") String newPassword,
                                 Model model, Principal principal) throws Exception {
        User currentUser = userService.findByUsername(principal.getName());
        if (currentUser == null) {
            throw new Exception("User not found");
        }

        //*check email already exists
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && !existingUser.getId().equals(currentUser.getId())) {
            model.addAttribute("emailExists", true);
            return "myProfile";
        }
        /*update password*/
        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbPassword = currentUser.getPassword();
            if (passwordEncoder.matches(user.getPassword(), dbPassword)) {
                currentUser.setPassword(passwordEncoder.encode(newPassword));
            } else {
                model.addAttribute("incorrectPassword", true);
                return "myProfile";
            }
        }
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        userService.save(currentUser);
        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUser);
        userSecurityService.authenticateUser(currentUser.getUsername());
        return "myProfile";
    }
}
