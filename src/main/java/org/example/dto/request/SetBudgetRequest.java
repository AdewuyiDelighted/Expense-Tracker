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
    private int startHour;
    private int startMinute;
    private int startSeconds;
    private int endDate;
    private int endMonth;
    private int endYear;
    private int endHour;
    private int endMinute;
    private int endSeconds;


//    public String getEmail() {
//        return email;
//    }
//
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public StartDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(StartDate startDate) {
//        String formattedStartDate = startDate.getYear() + "/" + startDate.getMonth() + "/" + startDate.getDay();
//        StartDate startDate1 =

//    }
//
//
//    public LocalDate getEndDate() {
//        return endDate;
//
//    }
//
//    public void setEndDate(EndDate endDate) {
//        String formattedEndDate = endDate.getYear() + "/" + endDate.getMonth() + "/" + endDate.getDate();
//        dateChecker(formattedEndDate);
//        if (LocalDate.parse(formattedEndDate, dateTimeFormatter).isBefore(LocalDate.now()))
//            throw new InvalidDateFormatException("Invalid date");
//        this.endDate = LocalDate.parse(formattedEndDate, dateTimeFormatter);
//    }


}
