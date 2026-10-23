package org.example.businesslogic.discount.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.discount.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends InMemoryRepository<Category> {

    public CategoryRepository() {
        save(new Category(null, "Electronics", 10));
        save(new Category(null, "Clothes", 20));
        save(new Category(null, "Books", 5));
    }
}
