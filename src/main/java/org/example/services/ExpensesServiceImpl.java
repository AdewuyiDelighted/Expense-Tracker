package org.example.services;

import org.example.data.model.Expense;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.repository.ExpenseRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.AddExpenseRequest;
import org.example.exception.InvalidDetailsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.utils.Mapper.map;

@Service
public class ExpensesServiceImpl implements ExpenseService {
    @Autowired
    ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ExpenseRepository expenseRepository;


    @Override
    public Expense addExpenses(AddExpenseRequest addExpenseRequest) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(addExpenseRequest.getEmail());
        if (expensesTrackerApp.isPresent()) {
            Expense expense = map(addExpenseRequest,expensesTrackerApp.get(),categoryService);
            if(expensesTrackerApp.get().isActiveBudget()){
                expense.setBudgetIsActive(true);
            }
            expenseRepository.save(expense);
            return expense;
        }
        throw new InvalidDetailsException("Detail Invalid");
    }

    @Override
    public List<Expense> getAllExpenseBelongingTo(Long expenseTrackerId) {
        if (expensesTrackerAppRepository.findById(expenseTrackerId).isEmpty()) throw new InvalidDetailsException("Invalid detail");
        ArrayList<Expense> allUserExpense = new ArrayList<>();
        for (Expense expense : expenseRepository.findAll()) {
            if (expense.getExpensesTrackerApp().getId().equals(expenseTrackerId)) {
                allUserExpense.add(expense);
            }
        }
        return allUserExpense;
    }


}
