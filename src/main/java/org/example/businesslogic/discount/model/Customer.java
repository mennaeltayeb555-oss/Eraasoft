package org.example.businesslogic.discount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements BaseEntity {

    private Long id;
    private String name;
    private boolean vip;
}
