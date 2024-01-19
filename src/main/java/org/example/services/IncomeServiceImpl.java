package org.example.services;

import org.example.data.model.Category;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.model.Income;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.data.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private ExpenseTrackerAppService expenseTrackerAppService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;

    @Override
    public Income addIncome(String incomeCategoryName,double amount,Long expenseTrackerId) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findById(expenseTrackerId);
        Income income = new Income();
        if(expensesTrackerApp.isPresent()) {
            income.setAmount(amount);
            income.setExpensesTrackerApp(expensesTrackerApp.get());
//            Category category = categoryService.addCategory(incomeCategoryName,expenseTrackerId);
            income.setCategory(categoryService.addCategory(incomeCategoryName,expenseTrackerId));
            incomeRepository.save(income);
        }
        return income;
    }
}
