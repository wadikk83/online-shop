package by.wadikk.onlineshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int stock;

    private double price;
    private String picture;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Brand> brands;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> categories;

    public boolean hasStock(Integer quantity) {
        if (this.stock >= quantity) {
            return true;
        } else return false;
    }

    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }
}
