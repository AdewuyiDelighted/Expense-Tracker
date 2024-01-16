package org.example.exception;

public class UserAlreadyExistException extends ExpensesTrackerException{
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
