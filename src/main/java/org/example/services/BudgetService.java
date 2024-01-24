package org.example.services;

import org.example.data.model.Budget;
import org.example.dto.request.ResetBudgetRequest;
import org.example.dto.request.SetBudgetRequest;

import java.util.List;

public interface BudgetService {
    Budget setBudget(SetBudgetRequest setBudgetRequest);
    Budget findBudget(String email);
    List<Budget> findAllBudget(String email);
    double getBudgetBalance(String email);
    String endBudget(String email);
    Budget resetBudget(ResetBudgetRequest request);


}
