package org.example.exception;

public class BudgetCanNotBeEnableException extends ExpensesTrackerException{
    public BudgetCanNotBeEnableException(String message) {
        super(message);
    }
}
