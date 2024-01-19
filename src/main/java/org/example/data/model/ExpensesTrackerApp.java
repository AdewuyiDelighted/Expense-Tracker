package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ExpensesTrackerApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;
    private double balance;
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate;
    @OneToMany(mappedBy = "expensesTrackerApp",cascade = CascadeType.ALL)
    private List<Income> userIncome = new ArrayList<>();
    @OneToMany(mappedBy = "expensesTrackerApp",cascade = CascadeType.ALL)
    private List<Expense> expense = new ArrayList<>();
    private boolean isLocked;
}
