package org.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jdk.jfr.Category;
import lombok.Data;


@Data
@Entity
public class Expense {
    @Id
    private Long id;
    private String name;
    private String amount;
    //private Category category;
    private String expenseTrackerAppId;

}
