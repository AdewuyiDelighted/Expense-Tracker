package org.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class ExpensesTrackerApp {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private double balance;
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate;
}
