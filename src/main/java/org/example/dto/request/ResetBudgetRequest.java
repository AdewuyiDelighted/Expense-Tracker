package org.example.dto.request;

import lombok.Data;

@Data
public class ResetBudgetRequest {
    private String email;
    private String newDate;
    private String newAmount;
}
