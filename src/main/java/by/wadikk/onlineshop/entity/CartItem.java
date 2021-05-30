package by.wadikk.onlineshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "article_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public void addQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }


    //https://metanit.com/java/tutorial/12.2.php
    public BigDecimal getSubtotal() {
        return new BigDecimal(product.getPrice()).multiply(new BigDecimal(quantity));

    }
}