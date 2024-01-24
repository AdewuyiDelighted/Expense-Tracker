package org.example.utils;

import org.example.data.model.Budget;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.SetBudgetRequest;
import org.example.exception.InvalidBudgetAmountException;
import org.example.exception.InvalidDetailsException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class Mapper {
    public static ExpensesTrackerApp map(RegisterRequest registerRequest) {
        ExpensesTrackerApp expensesTrackerApp = new ExpensesTrackerApp();
        expensesTrackerApp.setEmail(registerRequest.getEmail());
        expensesTrackerApp.setPassword(registerRequest.getPassword());
        return expensesTrackerApp;

    }

    public static Budget map(SetBudgetRequest setBudgetRequest,Long expenseAppTrackerId) {
        Budget budget = new Budget();
        budget.setBudgetAmount(setBudgetRequest.getAmount());
        budget.setStartDate(setBudgetRequest.getStartDate());
        budget.setEndDate(setBudgetRequest.getEndDate());
        budget.setActive(true);
        budget.setExpenseAppTrackerId(expenseAppTrackerId);
        budget.setBudgetBalance(setBudgetRequest.getAmount());
        return budget;
    }
}

