package org.example.services;

import org.example.data.model.Category;
import org.example.data.model.CategoryType;
import org.example.data.model.Expense;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.repository.ExpenseRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.exception.InvalidAmountException;
import org.example.exception.InvalidDetailsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
        if (expensesTrackerApp.isPresent()) {
            Expense expense = new Expense();
            expense.setExpensesTrackerApp(expensesTrackerApp.get());
            expense.setAmount(amount);
            expense.setCategory(categoryService.addCategory(categoryName, expenseTrackerId, CategoryType.EXPENSE));
            expense.setDateAdded(LocalDate.now());
            if(expensesTrackerApp.get().isActiveBudget()){
                expense.setBudgetActive(true);
            }
            expenseRepository.save(expense);
            return expense;
        }
        throw new InvalidDetailsException("Detail Invalid");
    }

    @Override
    public List<Expense> getAllExpenseBelongingTo(Long expenseTrackerId) {
        if (expensesTrackerAppRepository.findById(expenseTrackerId).isEmpty())
            throw new InvalidDetailsException("Invalid detail");
        ArrayList<Expense> allUserExpense = new ArrayList<>();
        for (Expense expense : expenseRepository.findAll()) {
            if (expense.getExpensesTrackerApp().getId().equals(expenseTrackerId)) {
                allUserExpense.add(expense);
            }
        }
        return allUserExpense;
    }

}
