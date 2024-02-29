package org.example.utils;

import org.example.data.model.ExpensesTrackerApp;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.request.SetBudgetRequest;
import org.example.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public class Verification {
    public static boolean isRegistered(String email, ExpensesTrackerAppRepository expensesTrackerAppRepository) {
        for (ExpensesTrackerApp expensesTrackerApp : expensesTrackerAppRepository.findAll()) {
            if (expensesTrackerApp.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public static void validateNewUserInfo(RegisterRequest registerRequest, ExpensesTrackerAppRepository expensesTrackerAppRepository) {
        if (isRegistered(registerRequest.getEmail(), expensesTrackerAppRepository))
            throw new UserAlreadyExistException("User already exist!!!");
        passwordChecker(registerRequest);
        bcrypt(registerRequest);
        if (!validateEmail(registerRequest.getEmail()))
            throw new InvalidEmailFormatException("Invalid email format");
    }

    public static void bcrypt(RegisterRequest registerRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        registerRequest.setPassword(encodedPassword);
    }

    public static void validateEmailAndPassword(LoginRequest loginRequest, ExpensesTrackerApp expensesTrackerApp) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), expensesTrackerApp.getPassword()))
            throw new InvalidDetailsException("Invalid details");
    }

    public static void validateAmount(SetBudgetRequest setBudgetRequest, ExpensesTrackerApp expensesTrackerApp) {
        if (setBudgetRequest.getAmount() <= 0) throw new InvalidBudgetAmountException("Enter a valid amount !!!!");
        if (setBudgetRequest.getAmount() > expensesTrackerApp.getBalance())
            throw new InvalidBudgetAmountException("Account Balance is too low for budget amount!!!!");
    }

    public static void validateBudgetDateDetails(SetBudgetRequest setBudgetRequest) {
        dateChecker(String.valueOf(setBudgetRequest.getStartYear()), String.valueOf(setBudgetRequest.getStartMonth()), String.valueOf(setBudgetRequest.getStartDate()));
        dateChecker(String.valueOf(setBudgetRequest.getEndYear()), String.valueOf(setBudgetRequest.getEndMonth()), String.valueOf(setBudgetRequest.getEndDate()));
        if (LocalDate.of(setBudgetRequest.getStartYear(), setBudgetRequest.getStartMonth(), setBudgetRequest.getStartDate()).isBefore(LocalDate.now()))
            throw new InvalidDateException("Invalid date");
        if (LocalDate.of(setBudgetRequest.getEndYear(), setBudgetRequest.getEndMonth(), setBudgetRequest.getEndDate()).isBefore(LocalDate.now()))
            throw new InvalidDateException("Invalid date");
    }

    public static boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        return email.matches(regex);
    }

    public static void passwordChecker(RegisterRequest registerRequest) {
        String regex = "^(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[@#$%^&-+=()])" + "(?=\\S+$).{8,20}$";
        if (!Pattern.matches(regex, registerRequest.getPassword()))
            throw new PasswordTooWeakException("Password too weak");
    }

    public static void dateChecker(String year, String month, String date) {
        String regexYear = "^20[0-2][0-9]$";
        String regexMonth = "^(([1-9])|(1[0-2]))$";
        String regexDay = "^(3[01]|[12][0-9]|0?[1-9])$";
        if (!Pattern.matches(regexYear, year) || !Pattern.matches(regexMonth, month) || !Pattern.matches(regexDay, date))
            throw new InvalidDateFormatException("Invalid date format");

    }

}
