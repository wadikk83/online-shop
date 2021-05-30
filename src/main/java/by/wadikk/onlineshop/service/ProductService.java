package by.wadikk.onlineshop.service;

import by.wadikk.onlineshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Page<Product> findProductByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh,
                                        List<String> categories, List<String> brands, String search);

    Product findProductById(Long id);

    Product saveProduct(Product product);

    void deleteProductById(Long id);

    List<String> getAllCategories();

    List<String> getAllBrands();
}
