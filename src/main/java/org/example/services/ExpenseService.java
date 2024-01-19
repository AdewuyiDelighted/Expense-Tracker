package org.example.services;

import org.example.data.model.Expense;

public interface ExpenseService {
    Expense addExpenses(String categoryName, double amount,Long expenseTrackerId);
}
