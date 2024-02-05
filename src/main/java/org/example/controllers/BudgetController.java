package org.example.controllers;

import org.example.dto.request.ResetBudgetRequest;
import org.example.dto.request.SetBudgetRequest;
import org.example.dto.response.*;
import org.example.exception.ExpensesTrackerException;
import org.example.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @PostMapping("/setUp")
    public ResponseEntity<?> setUpBudget(@RequestBody SetBudgetRequest setBudgetRequest) {
        SetUpResponse setUpResponse = new SetUpResponse();
        try {
            budgetService.setBudget(setBudgetRequest);
            setUpResponse.setData("Budget set up Successfully");
            return new ResponseEntity<>(new ApiResponse(setUpResponse, true), HttpStatus.CREATED);
        } catch (ExpensesTrackerException ex) {
            setUpResponse.setData(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(setUpResponse, false), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/checkBudgetBalance")
    public ResponseEntity<?> getBudgetBalance(@RequestParam(name = "email") String email) {
        GetBudgetBalanceResponse getBudgetBalanceResponse = new GetBudgetBalanceResponse();
        try {
            budgetService.getBudgetBalance(email);
            getBudgetBalanceResponse.setData("The balance of your running budget is  #" + budgetService.getBudgetBalance(email));
            return new ResponseEntity<>(new ApiResponse(getBudgetBalanceResponse, true), HttpStatus.FOUND);
        } catch (ExpensesTrackerException ex) {
            getBudgetBalanceResponse.setData(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(getBudgetBalanceResponse, false), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/findABudget")
    public ResponseEntity<?> viewABudget(@RequestParam(name = "email") String email) {
        ViewABudgetResponse viewABudgetResponse = new ViewABudgetResponse();
        try {
            viewABudgetResponse.setData(budgetService.findARecentBudget(email));
            return new ResponseEntity<>(new ApiResponse(viewABudgetResponse, true), HttpStatus.FOUND);
        } catch (ExpensesTrackerException ex) {
            viewABudgetResponse.setData(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(viewABudgetResponse, false), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/findAllBudget")
    public ResponseEntity<?> viewAllBudget(@RequestParam(name = "email") String email) {
        ViewAllBudgetResponse viewAllBudgetResponse = new ViewAllBudgetResponse();
        try {
            viewAllBudgetResponse.setData(budgetService.findAllBudgetBelongingTo(email));
            return new ResponseEntity<>(new ApiResponse(viewAllBudgetResponse, true), HttpStatus.FOUND);
        } catch (ExpensesTrackerException ex) {
            viewAllBudgetResponse.setData(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(viewAllBudgetResponse, false), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/endABudget")
    public ResponseEntity<?> endABudget(@RequestParam(name = "email") String email) {
        EndABudgetResponse endABudgetResponse = new EndABudgetResponse();
        try {
            budgetService.endBudget(email);
            endABudgetResponse.setData("Budget Ended Successfully");
            return new ResponseEntity<>(new ApiResponse(endABudgetResponse, true), HttpStatus.FOUND);
        } catch (ExpensesTrackerException ex) {
            endABudgetResponse.setData(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(endABudgetResponse, false), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/resetBudget")
    public ResponseEntity<?> resetBudget(@RequestBody ResetBudgetRequest resetBudgetRequest) {
        ResetBudgetResponse resetBudgetResponse = new ResetBudgetResponse();
        try {
            budgetService.resetBudget(resetBudgetRequest);
            resetBudgetResponse.setMessage("Budget updated successfully");
            return new ResponseEntity<>(new ApiResponse(resetBudgetResponse, true), HttpStatus.FOUND);
        } catch (ExpensesTrackerException ex) {
            resetBudgetResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(new ApiResponse(resetBudgetResponse, false), HttpStatus.FORBIDDEN);
        }
    }


}
