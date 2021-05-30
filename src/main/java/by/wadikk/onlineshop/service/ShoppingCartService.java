package by.wadikk.onlineshop.service;


import by.wadikk.onlineshop.entity.CartItem;
import by.wadikk.onlineshop.entity.Product;
import by.wadikk.onlineshop.entity.ShoppingCart;
import by.wadikk.onlineshop.entity.User;

public interface ShoppingCartService {

    ShoppingCart getShoppingCart(User user);

    CartItem addProductToShoppingCart(Product product, User user, int quantity);

    CartItem findCartItemById(Long id);

    void removeCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem, Integer quantity);
}
