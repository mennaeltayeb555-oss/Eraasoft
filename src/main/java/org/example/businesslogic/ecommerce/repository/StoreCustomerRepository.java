package org.example.businesslogic.ecommerce.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.ecommerce.model.StoreCustomer;
import org.springframework.stereotype.Repository;

@Repository
public class StoreCustomerRepository extends InMemoryRepository<StoreCustomer> {

    public StoreCustomerRepository() {
        save(new StoreCustomer(null, "Ali Hassan", "ali@mail.com"));
        save(new StoreCustomer(null, "Mai Tamer", "mai@mail.com"));
    }
}
