package org.example.exception;

public class InvalidDetailsException extends ExpensesTrackerException{
    public InvalidDetailsException(String message) {
        super(message);
    }
}
