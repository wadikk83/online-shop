package by.wadikk.onlineshop.entity;


import java.util.List;


public class ShoppingCart {

    private List<CartItem> cartItems;

    public ShoppingCart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    public CartItem findCartItemByProduct(Long id) {
        for (CartItem item : this.cartItems) {
            if (item.getProduct().getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
