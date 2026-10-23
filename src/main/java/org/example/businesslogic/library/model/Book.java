package org.example.businesslogic.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements BaseEntity {

    private Long id;
    private String title;
    private String author;
    private int availableCopies;
}
