package org.example.utils;

import org.example.data.model.*;
import org.example.dto.request.AddExpenseRequest;
import org.example.dto.request.AddIncomeRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.SetBudgetRequest;
import org.example.exception.InvalidDateException;
import org.example.exception.InvalidDateFormatException;
import org.example.services.CategoryService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.example.utils.Verification.dateChecker;
import static org.example.utils.Verification.validateBudgetDateDetails;

public class Mapper {
    public static ExpensesTrackerApp map(RegisterRequest registerRequest) {
        ExpensesTrackerApp expensesTrackerApp = new ExpensesTrackerApp();
        expensesTrackerApp.setEmail(registerRequest.getEmail());
        expensesTrackerApp.setPassword(registerRequest.getPassword());
        return expensesTrackerApp;
    }

    public static Budget map(SetBudgetRequest setBudgetRequest, ExpensesTrackerApp expensesTrackerApp) {
        validateBudgetDateDetails(setBudgetRequest);
        Budget budget = new Budget();
        budget.setExpenseAppTrackerId(expensesTrackerApp.getId());
        budget.setActive(true);
        budget.setBudgetAmount(setBudgetRequest.getAmount());
        budget.setStartDate(LocalDate.of(setBudgetRequest.getStartYear(), setBudgetRequest.getStartMonth(), setBudgetRequest.getStartDate()));
        budget.setEndDate(LocalDate.of(setBudgetRequest.getEndYear(), setBudgetRequest.getEndMonth(), setBudgetRequest.getEndDate()));
        budget.setBudgetBalance(budget.getBudgetAmount());
        return budget;
    }
    public static Expense map(AddExpenseRequest addExpenseRequest, ExpensesTrackerApp expenseTrackerApp, CategoryService categoryService){
        Expense expense = new Expense();
        expense.setExpensesTrackerApp(expenseTrackerApp);
        expense.setAmount(addExpenseRequest.getAmount());
        expense.setCategory(categoryService.addCategory(addExpenseRequest.getExpenseCategoryName(),expenseTrackerApp.getId(), CategoryType.EXPENSE));
        expense.setDateAdded(LocalDate.now());
        return expense;
    }
    public static Income map(AddIncomeRequest addIncomeRequest, ExpensesTrackerApp expenseTrackerApp, CategoryService categoryService) {
        Income income = new Income();
        income.setAmount(addIncomeRequest.getAmount());
        income.setExpensesTrackerApp(expenseTrackerApp);
        income.setDateAdded(LocalDate.now());
        income.setCategory(categoryService.addCategory(addIncomeRequest.getIncomeCategoryName(), expenseTrackerApp.getId(), CategoryType.INCOME));
        return income;
    }

}

