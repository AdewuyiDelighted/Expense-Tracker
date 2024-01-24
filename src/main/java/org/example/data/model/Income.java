package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;


@Data
@Entity
public class Income {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime dateAdded = LocalDateTime.now();
    private double amount;
    @OneToOne
    private Category category;
    @ManyToOne
    @JoinColumn(name = "expenses_Tracker_app_id")
    private ExpensesTrackerApp expensesTrackerApp;

}
