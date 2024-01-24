package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.exception.InvalidDateFormatException;
import org.example.utils.EndDate;
import org.example.utils.StartDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.example.utils.Verification.dateChecker;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SetBudgetRequest {
    private String email;
    private double amount;
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endDate;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(StartDate startDate) {
        String formattedStartDate = startDate.getDay() + "-" + startDate.getMonth() + "-" + startDate.getYear();
        dateChecker(formattedStartDate);
        if (LocalDateTime.parse(formattedStartDate, dateTimeFormatter).isBefore(LocalDateTime.now()))
            throw new InvalidDateFormatException("Invalid date");
        this.startDate = LocalDateTime.parse(formattedStartDate);
    }


    public LocalDateTime getEndDate() {
        return endDate;

    }

    public void setEndDate(EndDate endDate) {
        String formattedEndDate = endDate.getDate() + "-" + endDate.getMonth() + "-" + endDate.getYear();
        dateChecker(formattedEndDate);
        if (LocalDateTime.parse(formattedEndDate, dateTimeFormatter).isBefore(LocalDateTime.now()))
            throw new InvalidDateFormatException("Invalid date");
        this.endDate = LocalDateTime.parse(formattedEndDate, dateTimeFormatter);
    }


}
