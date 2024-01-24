package org.example.services;

import org.example.data.model.Income;

import java.util.List;

public interface IncomeService {
    Income addIncome(String incomeCategoryName,double amount, Long expenseTrackerId);

    List<Income> getAllIncomeBelongingTo(Long expenseTrackerId);
}
