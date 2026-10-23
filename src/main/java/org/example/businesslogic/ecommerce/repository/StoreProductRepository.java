package org.example.businesslogic.ecommerce.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.ecommerce.model.StoreProduct;
import org.springframework.stereotype.Repository;

@Repository
public class StoreProductRepository extends InMemoryRepository<StoreProduct> {

    public StoreProductRepository() {
        save(new StoreProduct(null, "Laptop", 25000, 5, true));
        save(new StoreProduct(null, "Mouse", 300, 50, true));
        save(new StoreProduct(null, "Keyboard", 700, 20, true));
        save(new StoreProduct(null, "Old Phone", 4000, 10, false));
    }
}
