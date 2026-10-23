package org.example.businesslogic.delivery.repository;

import org.example.businesslogic.common.repository.InMemoryRepository;
import org.example.businesslogic.delivery.model.DeliveryVehicle;
import org.example.businesslogic.delivery.model.Driver;
import org.springframework.stereotype.Repository;

@Repository
public class DriverRepository extends InMemoryRepository<Driver> {

    public DriverRepository() {
        save(new Driver(null, "Hassan", DeliveryVehicle.BIKE));
        save(new Driver(null, "Amr", DeliveryVehicle.CAR));
        save(new Driver(null, "Fady", DeliveryVehicle.VAN));
        save(new Driver(null, "Samy", DeliveryVehicle.TRUCK));
    }
}
