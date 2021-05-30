package by.wadikk.onlineshop.repository;

import by.wadikk.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select DISTINCT c.name from Category c ORDER by c.name")
    List<String> findAllCategories();


    @Query("select DISTINCT b.name from Brand b ORDER by b.name")
    List<String> findAllBrands();

}
