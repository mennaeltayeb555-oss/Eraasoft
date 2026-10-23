package org.example.businesslogic.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTable implements BaseEntity {

    private Long id;
    private int tableNumber;
    private int capacity;
}
