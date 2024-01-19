package org.example.services;

import org.example.data.model.Category;
import org.example.data.model.Expense;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.repository.ExpenseRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpensesServiceImpl implements ExpenseService {
    @Autowired
    ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public Expense addExpenses(String categoryName, double amount, Long expenseTrackerId) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findById(expenseTrackerId);
        Expense expense = new Expense();
//        Category category = categoryService.addCategory(categoryName, expenseTrackerId);
        expense.setExpensesTrackerApp(expensesTrackerApp.get());
        expense.setAmount(amount);
//        expense.setCategory(category);
        expenseRepository.save(expense);
        return expense;

    }
}
