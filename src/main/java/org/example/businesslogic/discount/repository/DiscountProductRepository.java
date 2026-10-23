package org.example.businesslogic.discount.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.discount.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountProductRepository extends InMemoryRepository<Product> {

    public DiscountProductRepository() {
        save(new Product(null, "Headphones", 800, 1L, true));
        save(new Product(null, "iPhone 15", 40000, 1L, false));
        save(new Product(null, "Jacket", 1200, 2L, true));
        save(new Product(null, "Clean Code", 450, 3L, true));
    }
}
