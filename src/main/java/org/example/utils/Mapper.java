package org.example.utils;

import org.example.data.model.ExpensesTrackerApp;
import org.example.dto.request.RegisterRequest;

public class Mapper {
    public static ExpensesTrackerApp map(RegisterRequest registerRequest) {
        ExpensesTrackerApp expensesTrackerApp = new ExpensesTrackerApp();
        expensesTrackerApp.setEmail(registerRequest.getEmail());
        expensesTrackerApp.setPassword(registerRequest.getPassword());
        return expensesTrackerApp;
    }
}
