package org.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jdk.jfr.Category;
import lombok.Data;


@Data
@Entity
public class Expense {
    @Id
    private Long id;
    private String name;
    private String amount;
    @OneToOne
    private Category category;
    private String expenseTrackerAppId;

}
