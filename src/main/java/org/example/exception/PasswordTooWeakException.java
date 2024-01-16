package org.example.exception;

public class PasswordTooWeakException extends ExpensesTrackerException{
    public PasswordTooWeakException(String message) {
        super(message);
    }
}
