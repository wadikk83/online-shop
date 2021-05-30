package by.wadikk.onlineshop.repository;


import by.wadikk.onlineshop.entity.Brand;
import by.wadikk.onlineshop.entity.Category;
import by.wadikk.onlineshop.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class ProductSpecification {

    private ProductSpecification() {
    }

    //https://easyjava.ru/spring/spring-data-project/ispolzovanie-jpa-criteria-v-spring-data-jpa/

    public static Specification<Product> filterBy(Integer priceLow, Integer priceHigh, List<String> categories,
                                                  List<String> brands, String search) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery,
                                                                    CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                criteriaQuery.distinct(true);

                if (categories!=null && !categories.isEmpty()) {
                    Join<Product, Category> joinSize = root.join("categories");
                    predicates.add(criteriaBuilder.and(joinSize.get("name").in(categories)));
                }
                if (brands!=null && !brands.isEmpty()) {
                    Join<Product, Brand> joinSize = root.join("brands");
                    predicates.add(criteriaBuilder.and(joinSize.get("name").in(brands)));
                }

                if(search!=null && !search.isEmpty()) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("title"), "%"+search+"%")));
                }
                if (priceLow!=null && priceLow >= 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceLow)));
                }
                if (priceHigh!=null && priceHigh >= 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceHigh)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
