package org.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class FindHistory {
    @Id
    private Long id;

//    private List<Income> allIncome;
//    private List<Expense> allExpense;


}
