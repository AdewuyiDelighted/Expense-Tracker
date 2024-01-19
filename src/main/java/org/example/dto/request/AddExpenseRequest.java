package org.example.dto.request;


import lombok.Data;

@Data
public class AddExpenseRequest {
    private String expenseCategoryName;
    private double amount;
    private String email;
}
