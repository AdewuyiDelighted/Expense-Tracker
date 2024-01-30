package org.example.services;

import org.example.data.model.*;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.data.repository.IncomeRepository;
import org.example.exception.InvalidAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        if(amount <= 0 ) throw new InvalidAmountException("Enter a valid amount");
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findById(expenseTrackerId);
        Income income = new Income();
        if(expensesTrackerApp.isPresent()) {
            income.setAmount(amount);
            income.setExpensesTrackerApp(expensesTrackerApp.get());
            income.setDateAdded(LocalDate.of(2024,01,28));
            income.setCategory(categoryService.addCategory(incomeCategoryName,expenseTrackerId, CategoryType.INCOME));
            incomeRepository.save(income);
        }
        return income;
    }

    @Override
    public List<Income> getAllIncomeBelongingTo(Long expenseTrackerId) {
            ArrayList<Income> allUserIncome = new ArrayList<>();
            for (Income income : incomeRepository.findAll()) {
                if (income.getExpensesTrackerApp().getId() == expenseTrackerId) {
                    allUserIncome.add(income);
                }
            }
            return allUserIncome;
        }

    }

