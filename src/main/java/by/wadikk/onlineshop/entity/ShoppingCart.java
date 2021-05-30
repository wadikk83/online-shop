package by.wadikk.onlineshop.entity;


import java.math.BigDecimal;
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

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public BigDecimal getTotal() {
        BigDecimal cartTotal = new BigDecimal(0);
        for (CartItem item : this.cartItems) {
            cartTotal = cartTotal.add(item.getSubtotal());
        }
        return cartTotal;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
