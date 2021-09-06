package com.example.ecommerce.integration.repository;

import com.example.ecommerce.integration.model.Category;
import com.example.ecommerce.integration.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    void deleteByCategory(Category category);
    void deleteByCode(String code);
    Product findProductByCode(String code);

}
