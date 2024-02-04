package org.example.services;

import org.example.data.model.Budget;
import org.example.dto.request.SetBudgetRequest;

import java.util.List;

public interface BudgetService {
    Budget setBudget(SetBudgetRequest setBudgetRequest);

    double getBudgetBalance(String mail);

    Budget findARecentBudget(String mail);

    List<Budget> findAllBudgetBelongingTo(String email);
}
