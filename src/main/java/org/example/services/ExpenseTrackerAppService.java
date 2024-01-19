package org.example.services;

import org.example.data.model.ExpensesTrackerApp;
import org.example.data.model.Income;
import org.example.dto.request.AddExpenseRequest;
import org.example.dto.request.AddIncomeRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public interface ExpenseTrackerAppService {

    void register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);
    Optional<ExpensesTrackerApp> findById(Long expenseTrackerId);

   void addIncome(AddIncomeRequest addIncomeRequest);
   double getBalance(String email);

    void addExpenses(AddExpenseRequest addExpenseRequest);
//void addIncome(Income income);


}
