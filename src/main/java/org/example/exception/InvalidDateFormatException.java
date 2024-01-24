package org.example.exception;

public class InvalidDateFormatException extends ExpensesTrackerException{
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
