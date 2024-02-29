package org.example.services;

import org.example.data.model.Income;
import org.example.dto.request.AddIncomeRequest;

import java.util.List;

public interface IncomeService {
    Income addIncome(AddIncomeRequest addIncomeRequest);

    List<Income> getAllIncomeBelongingTo(Long expenseTrackerId);
}
