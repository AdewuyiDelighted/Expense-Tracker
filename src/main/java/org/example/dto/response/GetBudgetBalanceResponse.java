package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class GetBudgetBalanceResponse {
    private String message;
    private boolean isSuccessful;
}
