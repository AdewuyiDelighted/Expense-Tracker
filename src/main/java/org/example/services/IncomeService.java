package org.example.services;

import org.example.data.model.Income;

public interface IncomeService {
    Income addIncome(String incomeCategoryName,double amount, Long expenseTrackerId);
}
