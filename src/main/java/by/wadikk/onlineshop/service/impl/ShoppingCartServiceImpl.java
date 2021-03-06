package by.wadikk.onlineshop.service.impl;

import by.wadikk.onlineshop.entity.CartItem;
import by.wadikk.onlineshop.entity.Product;
import by.wadikk.onlineshop.entity.ShoppingCart;
import by.wadikk.onlineshop.entity.User;
import by.wadikk.onlineshop.repository.CartItemRepository;
import by.wadikk.onlineshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public ShoppingCart getShoppingCart(User user) {
        return new ShoppingCart(cartItemRepository.findAllByUser(user));
    }

    @Override
    public CartItem addProductToShoppingCart(Product product, User user, int quantity) {

        ShoppingCart shoppingCart = this.getShoppingCart(user);
        CartItem cartItem = shoppingCart.findCartItemByProduct(product.getId());
        if (cartItem != null) {
            cartItem.addQuantity(quantity);
            cartItem = cartItemRepository.save(cartItem);
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem = cartItemRepository.save(cartItem);
        }
        return cartItem;
    }

    @Override
    public CartItem findCartItemById(Long id) {
        Optional<CartItem> optional = cartItemRepository.findById(id);
        return optional.get();
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        cartItemRepository.deleteById(cartItem.getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem, Integer quantity) {
        if (cartItem.getProduct().hasStock(quantity)) {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }

}
