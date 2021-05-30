package by.wadikk.onlineshop.controller;


import by.wadikk.onlineshop.entity.CartItem;
import by.wadikk.onlineshop.entity.Product;
import by.wadikk.onlineshop.entity.ShoppingCart;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.service.ProductService;
import by.wadikk.onlineshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/cart")
    public String shoppingCart(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);
        model.addAttribute("cartItemList", shoppingCart.getCartItems());
        model.addAttribute("shoppingCart", shoppingCart);
        return "shoppingCart";
    }

    @RequestMapping("/add-item")
    public String addItem(@ModelAttribute("product") Product product, @RequestParam("quantity") String quantity,
                          RedirectAttributes attributes, Authentication authentication) {

        product = productService.findProductById(product.getId());
        if (product.getStock() < (Integer.parseInt(quantity))) {
            attributes.addFlashAttribute("notEnoughStock", true);
            return "redirect:/product-detail?id=" + product.getId();
        }
        User user = (User) authentication.getPrincipal();
        shoppingCartService.addProductToShoppingCart(product, user, Integer.parseInt(quantity));
        attributes.addFlashAttribute("addProductSuccess", true);
        return "redirect:/product-detail?id=" + product.getId();
    }

    @PostMapping("/update-item")
    public String updateItemQuantity(@RequestParam("id") Long cartItemId,
                                     @RequestParam("quantity") Integer quantity, Model model) {
        CartItem cartItem = shoppingCartService.findCartItemById(cartItemId);
            shoppingCartService.updateCartItem(cartItem, quantity);
        return "redirect:/shopping-cart/cart";
    }

    @RequestMapping("/remove-item")
    public String removeItem(@RequestParam("id") Long id) {
        shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));
        return "redirect:/shopping-cart/cart";
    }
}
