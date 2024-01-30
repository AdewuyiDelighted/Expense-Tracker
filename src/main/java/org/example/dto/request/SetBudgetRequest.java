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
@AllArgsConstructor
@RequiredArgsConstructor
public class SetBudgetRequest {
    private String email;
    private double amount;
    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate;


    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(StartDate startDate) {
        String formattedStartDate = startDate.getYear() + "/" + startDate.getMonth() + "/" + startDate.getDay();
//        dateChecker(formattedStartDate);
        if (LocalDate.parse(formattedStartDate, dateTimeFormatter).isBefore(LocalDate.now()))
            throw new InvalidDateFormatException("Invalid date");
        this.startDate = LocalDate.parse(formattedStartDate, dateTimeFormatter);
    }


    public LocalDate getEndDate() {
        return endDate;

    }

    public void setEndDate(EndDate endDate) {
        String formattedEndDate = endDate.getYear() + "/" + endDate.getMonth() + "/" + endDate.getDate();
//        dateChecker(formattedEndDate);
        if (LocalDate.parse(formattedEndDate, dateTimeFormatter).isBefore(LocalDate.now()))
            throw new InvalidDateFormatException("Invalid date");
        this.endDate = LocalDate.parse(formattedEndDate, dateTimeFormatter);
    }


}
