package by.wadikk.onlineshop.service.impl;

import by.wadikk.onlineshop.entity.Product;
import by.wadikk.onlineshop.repository.ProductRepository;
import by.wadikk.onlineshop.repository.ProductSpecification;
import by.wadikk.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findProductByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh,
                                               List<String> categories, List<String> brands, String search) {

        Page<Product> page = productRepository.findAll(ProductSpecification.filterBy(
                priceLow, priceHigh, categories, brands, search), pageable);
        return page;
    }


    @Override
    public Product findProductById(Long id) {
        Optional<Product> opt = productRepository.findById(id);
        return opt.get();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    @Override
    public List<String> getAllBrands() {
        return productRepository.findAllBrands();
    }
}
