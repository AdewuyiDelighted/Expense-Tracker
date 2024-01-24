package org.example.exception;

public class InvalidAmountException extends ExpensesTrackerException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
