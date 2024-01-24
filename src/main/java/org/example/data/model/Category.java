package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long expensesTrackerId;
    @Enumerated
    private CategoryType categoryType;
}
