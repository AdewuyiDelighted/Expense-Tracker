package org.example.dto.request;

import lombok.Data;
import org.example.utils.EndDate;
import org.example.utils.ResetDate;
import org.example.utils.StartDate;

import java.time.LocalDate;

@Data
public class ResetBudgetRequest {
    private String email;
    private EndDate newEndDate;
    private double newAmount;

}
