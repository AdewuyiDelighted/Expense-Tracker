package org.example.services;

import org.example.data.model.Expense;

import java.util.List;

public interface ExpenseService {
    Expense addExpenses(String categoryName, double amount,Long expenseTrackerId);
    List<Expense> getAllExpenseBelongingTo(Long expenseTrackerId);
}
