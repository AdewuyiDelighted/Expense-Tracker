package org.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Income {
    @Id
    private Long id;
    private String amount;
   //private Category category;
    private String name;
    private String expensesTrackerAppId;

}
