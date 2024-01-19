package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double amount;
    @OneToOne
    private Category category;
    @ManyToOne
    @JoinColumn(name = "expenses_tracker_app_id")
    private ExpensesTrackerApp expensesTrackerApp;

}
