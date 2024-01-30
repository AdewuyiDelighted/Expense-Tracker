package org.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private double budgetBalance;
    private double budgetAmount;
    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate;
    private Long expenseAppTrackerId;
    private boolean isActive;
}
