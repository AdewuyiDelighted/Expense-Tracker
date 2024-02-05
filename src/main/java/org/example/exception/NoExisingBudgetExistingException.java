package org.example.exception;

import org.example.data.model.ExpensesTrackerApp;

public class NoExisingBudgetExistingException extends ExpensesTrackerException{
    public NoExisingBudgetExistingException(String message) {
        super(message);
    }
}
