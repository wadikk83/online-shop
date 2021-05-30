package by.wadikk.onlineshop.controller;

import by.wadikk.onlineshop.entity.Order;
import by.wadikk.onlineshop.entity.ShoppingCart;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.service.OrderService;
import by.wadikk.onlineshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CheckoutController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(@RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField,
                           Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);
        if (shoppingCart.isEmpty()) {
            model.addAttribute("emptyCart", true);
            return "redirect:/shopping-cart/cart";
        }
        model.addAttribute("cartItemList", shoppingCart.getCartItems());
        model.addAttribute("shoppingCart", shoppingCart);
        if (missingRequiredField) {
            model.addAttribute("missingRequiredField", true);
        }
        return "checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(RedirectAttributes redirectAttributes, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);
        if (!shoppingCart.isEmpty()) {
            Order order = orderService.createOrder(shoppingCart, user);
            redirectAttributes.addFlashAttribute("order", order);
        }
        return "redirect:/order-submitted";
    }

    @GetMapping("/order-submitted")
    public String orderSubmitted(Model model) {
        Order order = (Order) model.asMap().get("order");
        if (order == null) {
            return "redirect:/";
        }
        model.addAttribute("order", order);
        return "orderSubmitted";
    }
}