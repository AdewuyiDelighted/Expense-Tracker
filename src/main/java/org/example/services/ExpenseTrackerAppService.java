package org.example.services;

import org.example.data.model.Budget;
import org.example.data.model.Expense;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.model.Income;
import org.example.dto.request.AddExpenseRequest;
import org.example.dto.request.AddIncomeRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ExpenseTrackerAppService {

    void register(RegisterRequest registerRequest);

    void login(LoginRequest loginRequest);

    Optional<ExpensesTrackerApp> findById(Long expenseTrackerId);

    void addIncome(AddIncomeRequest addIncomeRequest);

    double getBalance(String email);

    void addExpenses(AddExpenseRequest addExpenseRequest);

    List<Expense> findAllExpenseBelongingTo(String email);

    List<Income> findAllIncomeBelongingTo(String mail);

    List<Object> findHistory(String email);



}
