package org.example.services;

import org.example.data.model.Expense;
import org.example.data.model.ExpensesTrackerApp;
import org.example.dto.request.AddExpenseRequest;

import java.util.List;

public interface ExpenseService {
    Expense addExpenses(AddExpenseRequest addExpenseRequest);

    List<Expense> getAllExpenseBelongingTo(Long expenseTrackerId);
}
