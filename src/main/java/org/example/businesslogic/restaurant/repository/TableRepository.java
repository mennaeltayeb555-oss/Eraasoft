package org.example.businesslogic.restaurant.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.restaurant.model.RestaurantTable;
import org.springframework.stereotype.Repository;

@Repository
public class TableRepository extends InMemoryRepository<RestaurantTable> {

    public TableRepository() {
        save(new RestaurantTable(null, 1, 2));
        save(new RestaurantTable(null, 2, 4));
        save(new RestaurantTable(null, 3, 6));
        save(new RestaurantTable(null, 4, 10));
    }
}
