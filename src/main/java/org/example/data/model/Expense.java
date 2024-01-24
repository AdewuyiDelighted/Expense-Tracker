package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateAdded = LocalDateTime.now();
    private double amount;
    @OneToOne
    private Category category;
    @ManyToOne
    @JoinColumn(name = "expenses_tracker_app_id")
    private ExpensesTrackerApp expensesTrackerApp;

}
