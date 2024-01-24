package org.example.services;

import org.example.data.model.Budget;
import org.example.data.model.Expense;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.repository.BudgetRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.data.repository.IncomeRepository;
import org.example.dto.request.ResetBudgetRequest;
import org.example.dto.request.SetBudgetRequest;
import org.example.exception.BudgetCanNotBeEnableException;
import org.example.exception.InvalidBudgetAmountException;
import org.example.exception.InvalidDetailsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.utils.Mapper.map;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    ExpenseService expenseService;
    private double totalExpense;

    private final ArrayList<Budget> listOfUserBudget = new ArrayList<>();

    @Override
    public Budget setBudget(SetBudgetRequest setBudgetRequest) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(setBudgetRequest.getEmail());
        validateBudgetDetails(setBudgetRequest, expensesTrackerApp);
        Budget budget = map(setBudgetRequest, expensesTrackerApp.get().getId());
        budgetRepository.save(budget);
        listOfUserBudget.add(budget);
        return budget;
    }

    @Override
    public double getBudgetBalance(String email) {
        Budget budget = findBudget(email);
        for (Expense expense : expenseService.getAllExpenseBelongingTo(budget.getExpenseAppTrackerId())) {
            if (expense.getDateAdded().isAfter(budget.getStartDate())) {
                totalExpense += expense.getAmount();
            }
        }
        budget.setBudgetBalance(budget.getBudgetBalance() - totalExpense);
        return budget.getBudgetAmount();
    }

    @Override
    public Budget findBudget(String email) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(email);
        if (expensesTrackerApp.isPresent()) {
            return listOfUserBudget.getLast();
        }
        throw new InvalidDetailsException("Invalid detail");
    }

    @Override
    public String endBudget(String email) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(email);
        if (expensesTrackerApp.isPresent()) {
            listOfUserBudget.getLast().setActive(false);
        }
        return "Existing budget successfully ended";

    }

    @Override
    public Budget resetBudget(ResetBudgetRequest resetBudgetRequest) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(resetBudgetRequest.getEmail());

        if (expensesTrackerApp.isPresent()) {

        }
        return null;
    }


    @Override
    public List<Budget> findAllBudget(String email) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(email);
        if (expensesTrackerApp.isPresent()) {
            return listOfUserBudget;
        }
        throw new InvalidDetailsException("Detail Invalid");
    }

    private void validateBudgetDetails(SetBudgetRequest setBudgetRequest, Optional<ExpensesTrackerApp> expensesTrackerApp) {
        if (!listOfUserBudget.isEmpty()) {
            if (setBudgetRequest.getStartDate().isBefore(listOfUserBudget.getLast().getEndDate()) || listOfUserBudget.getLast().isActive())
                throw new BudgetCanNotBeEnableException("Budget can't be enable due to existing budget (Check for reset of existing budget)");
        } else {
            if (setBudgetRequest.getAmount() > expensesTrackerApp.get().getBalance())
                throw new InvalidBudgetAmountException("Balance too low for budget amount");
        }
    }
}


