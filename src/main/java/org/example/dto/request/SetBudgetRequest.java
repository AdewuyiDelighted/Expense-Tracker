package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.exception.InvalidDateFormatException;
import org.example.utils.EndDate;
import org.example.utils.StartDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.example.utils.Verification.dateChecker;

@Data

public class SetBudgetRequest {
    private String email;
    private double amount;
    private int startDate;
    private int startMonth;
    private int startYear;
    private int endDate;
    private int endMonth;
    private int endYear;




}
