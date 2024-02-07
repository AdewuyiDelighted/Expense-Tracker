package org.example.controllers;

import org.example.dto.request.AddExpenseRequest;
import org.example.dto.request.AddIncomeRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.*;
import org.example.exception.ExpensesTrackerException;
import org.example.services.ExpenseTrackerAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/request")
public class ExpenseTrackerController {
    @Autowired
    private ExpenseTrackerAppService expenseTrackerAppService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = new RegisterResponse();
        try {
            expenseTrackerAppService.register(registerRequest);
            registerResponse.setMessage("Registration successful");
            return new ResponseEntity<>(new ApiResponse(registerResponse, true), HttpStatus.CREATED);
        } catch (ExpensesTrackerException ex) {
            registerResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(registerResponse, false), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            expenseTrackerAppService.login(loginRequest);
            loginResponse.setMessage("Login successful");
            return new ResponseEntity<>(new ApiResponse(loginResponse, true), HttpStatus.ACCEPTED);
        } catch (ExpensesTrackerException ex) {
            loginResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(loginResponse, false), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addIncome")
    public ResponseEntity<?> addIncome(@RequestBody AddIncomeRequest addIncomeRequest) {
        AddIncomeResponse addIncomeResponse = new AddIncomeResponse();
        try {
            expenseTrackerAppService.addIncome(addIncomeRequest);
            addIncomeResponse.setMessage("Income added successfully");
            return new ResponseEntity<>(new ApiResponse(addIncomeResponse, true), HttpStatus.ACCEPTED);
        } catch (ExpensesTrackerException ex) {
            addIncomeResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(addIncomeResponse, false), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addExpense")
    public ResponseEntity<?> addExpense(@RequestBody AddExpenseRequest addExpenseRequest) {
        AddExpenseResponse addExpenseResponse = new AddExpenseResponse();
        try {
            expenseTrackerAppService.addExpenses(addExpenseRequest);
            addExpenseResponse.setMessage("Expense added successfully");
            return new ResponseEntity<>(new ApiResponse(addExpenseResponse, true), HttpStatus.ACCEPTED);
        } catch (ExpensesTrackerException ex) {
            addExpenseResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(addExpenseResponse, false), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getBalance")
    public ResponseEntity<?> checkBalance(@RequestParam(name = "email") String email){
        CheckBalanceResponse checkBalanceResponse = new CheckBalanceResponse();
        try{
            expenseTrackerAppService.getBalance(email);
            checkBalanceResponse.setMessage("Your balance is #"+expenseTrackerAppService.getBalance(email));
            return new ResponseEntity<>(new ApiResponse(checkBalanceResponse,true),HttpStatus.ACCEPTED);
        }catch (ExpensesTrackerException ex) {
            checkBalanceResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(checkBalanceResponse, false), HttpStatus.BAD_REQUEST);
        }
    }

}
