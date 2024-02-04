package org.example.services;


import lombok.extern.slf4j.Slf4j;
import org.example.data.model.Budget;
import org.example.data.model.Expense;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.repository.BudgetRepository;
import org.example.data.repository.ExpenseRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.SetBudgetRequest;
import org.example.exception.InvalidAmountException;
import org.example.exception.InvalidBudgetAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.utils.Mapper.map;


@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private ExpenseTrackerAppService expenseTrackerAppService;
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ExpenseService expenseService;

    @Override
    public Budget setBudget(SetBudgetRequest setBudgetRequest) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(setBudgetRequest.getEmail());
        if (expensesTrackerApp.isPresent()) {
            validateAmount(setBudgetRequest, expensesTrackerApp);
            Budget budget = map(setBudgetRequest, expensesTrackerApp.get());
            expensesTrackerApp.get().setActiveBudget(true);
            expensesTrackerAppRepository.save(expensesTrackerApp.get());
            budgetRepository.save(budget);
            return budget;
        }
        return null;
    }


    private static void validateAmount(SetBudgetRequest setBudgetRequest, Optional<ExpensesTrackerApp> expensesTrackerApp) {
        if (setBudgetRequest.getAmount() <= 0 || setBudgetRequest.getAmount() > expensesTrackerApp.get().getBalance())
            throw new InvalidBudgetAmountException("Account Balance is too low for budget amount!!!!");
    }

    @Override
    public double getBudgetBalance(String mail) {
        double totalExpenses = 0;
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(mail);
        if (expensesTrackerApp.isPresent()) {
            Budget budget = findARecentBudget(mail);
            for (Expense expense : expenseService.getAllExpenseBelongingTo(expensesTrackerApp.get().getId())) {
                if (expense.isBudgetActive()) {
                    totalExpenses += expense.getAmount();
                }

            }
            budget.setBudgetBalance(budget.getBudgetBalance() - totalExpenses);
            return budget.getBudgetBalance();
        }
        return 0;
    }

    @Override
    public Budget findARecentBudget(String mail) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(mail);
        if (expensesTrackerApp.isPresent()) {
            return lastElementOfUserBudget(findAllBudgetBelongingTo(mail));
        }
        return null;
    }

    @Override
    public List<Budget> findAllBudgetBelongingTo(String email) {
        ArrayList<Budget> listOfBudget = new ArrayList<>();
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(email);
        if (expensesTrackerApp.isPresent()) {
            for (Budget budget : budgetRepository.findAll()) {
                if (budget.getExpenseAppTrackerId().equals(expensesTrackerApp.get().getId())) listOfBudget.add(budget);
            }
        }
        return listOfBudget;
    }

    private static Budget lastElementOfUserBudget(List<Budget> listOfBudget) {
        return listOfBudget.get(listOfBudget.size() - 1);
    }
}



