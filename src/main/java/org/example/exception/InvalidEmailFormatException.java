package org.example.exception;

public class InvalidEmailFormatException extends ExpensesTrackerException{

    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
