package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateAdded = LocalDate.now();
    private double amount;
    @OneToOne
    private Category category;
    @ManyToOne
    @JoinColumn(name = "expenses_tracker_app_id")
    private ExpensesTrackerApp expensesTrackerApp;
    @ManyToOne
    @JoinColumn(name = "expenses_tracker_app_id")
    private FindHistory findHistory;
    private boolean budgetIsActive = false;

}
