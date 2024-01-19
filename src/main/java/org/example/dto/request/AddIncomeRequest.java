package org.example.dto.request;

import lombok.Data;

@Data
public class AddIncomeRequest {
    private String incomeCategoryName;
    private double amount;
//    private Long expenseTrackerId;
    private String email;

}
