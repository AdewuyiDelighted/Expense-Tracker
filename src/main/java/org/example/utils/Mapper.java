package org.example.utils;

import org.example.data.model.Budget;
import org.example.data.model.ExpensesTrackerApp;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.SetBudgetRequest;
import org.example.exception.InvalidDateException;
import org.example.exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.example.utils.Verification.dateChecker;

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

    private static void validateBudgetDateDetails(SetBudgetRequest setBudgetRequest) {
        dateChecker(String.valueOf(setBudgetRequest.getStartYear()), String.valueOf(setBudgetRequest.getStartMonth()), String.valueOf(setBudgetRequest.getStartDate()));
        dateChecker(String.valueOf(setBudgetRequest.getEndYear()), String.valueOf(setBudgetRequest.getEndMonth()), String.valueOf(setBudgetRequest.getEndDate()));
        if (LocalDate.of(setBudgetRequest.getStartYear(), setBudgetRequest.getStartMonth(), setBudgetRequest.getStartDate()).isBefore(LocalDate.now()))
            throw new InvalidDateException("Invalid date");
        if (LocalDate.of(setBudgetRequest.getEndYear(), setBudgetRequest.getEndMonth(), setBudgetRequest.getEndDate()).isBefore(LocalDate.now()))
            throw new InvalidDateException("Invalid date");
    }


}

