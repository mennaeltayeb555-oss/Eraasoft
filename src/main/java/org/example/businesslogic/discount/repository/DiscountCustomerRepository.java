package org.example.businesslogic.discount.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.discount.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountCustomerRepository extends InMemoryRepository<Customer> {

    public DiscountCustomerRepository() {
        save(new Customer(null, "Mohamed", false));
        save(new Customer(null, "Yasmin", true));
    }
}
