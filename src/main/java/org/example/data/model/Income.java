package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
public class Income {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate dateAdded = LocalDate.now();
    private double amount;
    @OneToOne
    private Category category;
    @ManyToOne
    @JoinColumn(name = "expenses_Tracker_app_id")
    private ExpensesTrackerApp expensesTrackerApp;
//    @ManyToOne
//    @JoinColumn(name = "expenses_Tracker_app_id")
//    private FindHistory findHistory;

}
