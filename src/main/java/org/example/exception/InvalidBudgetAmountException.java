package org.example.exception;

public class InvalidBudgetAmountException extends ExpensesTrackerException{
    public InvalidBudgetAmountException(String message) {
        super(message);
    }
}
