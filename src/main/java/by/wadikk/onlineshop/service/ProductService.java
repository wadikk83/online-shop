package by.wadikk.onlineshop.service;

import by.wadikk.onlineshop.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();


    List<Product> findFirstProducts();

    Product findProductById(Long id);

    Product saveProduct(Product product);

    void deleteProductById(Long id);

    List<String> getAllCategories();

    List<String> getAllBrands();
}
