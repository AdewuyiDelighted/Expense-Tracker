package org.example.exception;

public class InvalidDateException extends ExpensesTrackerException{
    public InvalidDateException(String message) {
        super(message);
    }
}
