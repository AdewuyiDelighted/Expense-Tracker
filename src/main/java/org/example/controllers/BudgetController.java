package org.example.controllers;

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
//    @Autowired
//    private BudgetService budgetService;
//
//    @GetMapping("/setUp")
//    public ResponseEntity<?> setUpBudget(@RequestBody SetBudgetRequest setBudgetRequest) {
//        SetUpResponse setUpResponse = new SetUpResponse();
//        try {
//            setUpResponse.setData(budgetService.setBudget(setBudgetRequest));
//            return new ResponseEntity<>(new ApiResponse(setUpResponse, true), HttpStatus.CREATED);
//        } catch (ExpensesTrackerException ex) {
//            setUpResponse.setData(ex.getMessage());
//            return new ResponseEntity<>(new ApiResponse(setUpResponse, false), HttpStatus.FORBIDDEN);
//        }
//    }
//
//    @GetMapping("/checkBudgetBalance")
//    public ResponseEntity<?> getBudgetBalance(@RequestParam(name = "email") String email) {
//        GetBudgetBalanceResponse getBudgetBalanceResponse = new GetBudgetBalanceResponse();
//        try {
//            budgetService.getBudgetBalance(email);
//            getBudgetBalanceResponse.setData("The balance of your running budget is  #" + budgetService.getBudgetBalance(email));
//            return new ResponseEntity<>(new ApiResponse(getBudgetBalanceResponse, true), HttpStatus.FOUND);
//        } catch (ExpensesTrackerException ex) {
//            getBudgetBalanceResponse.setData(ex.getMessage());
//            return new ResponseEntity<>(new ApiResponse(getBudgetBalanceResponse, false), HttpStatus.FORBIDDEN);
//        }
//    }
//
//    @GetMapping("/findABudget")
//    public ResponseEntity<?> viewABudget(@RequestParam(name = "email") String email) {
//        ViewABudgetResponse viewABudgetResponse = new ViewABudgetResponse();
//        try {
//            viewABudgetResponse.setData(budgetService.findBudget(email));
//            return new ResponseEntity<>(new ApiResponse(viewABudgetResponse, true), HttpStatus.FOUND);
//        } catch (ExpensesTrackerException ex) {
//            viewABudgetResponse.setData(ex.getMessage());
//            return new ResponseEntity<>(new ApiResponse(viewABudgetResponse, false), HttpStatus.FORBIDDEN);
//        }
//    }
//
//    @PostMapping("/findAllBudget")
//    public ResponseEntity<?> viewAllBudget(@RequestParam(name = "email") String email) {
//        ViewAllBudgetResponse viewAllBudgetResponse = new ViewAllBudgetResponse();
//        try {
//            viewAllBudgetResponse.setData(budgetService.findAllBudget(email));
//            return new ResponseEntity<>(new ApiResponse(viewAllBudgetResponse, true), HttpStatus.FOUND);
//        } catch (ExpensesTrackerException ex) {
//            viewAllBudgetResponse.setData(ex.getMessage());
//            return new ResponseEntity<>(new ApiResponse(viewAllBudgetResponse, false), HttpStatus.FORBIDDEN);
//        }
//    }
//
//    @PostMapping("/EndABudget")
//    public ResponseEntity<?> endABudget(@RequestParam(name = "email") String email) {
//        EndABudgetResponse endABudgetResponse = new EndABudgetResponse();
//        try {
//            endABudgetResponse.setData(budgetService.endBudget(email));
//            return new ResponseEntity<>(new ApiResponse(endABudgetResponse, true), HttpStatus.FOUND);
//        } catch (ExpensesTrackerException ex) {
//            endABudgetResponse.setData(ex.getMessage());
//            return new ResponseEntity<>(new ApiResponse(endABudgetResponse, false), HttpStatus.FORBIDDEN);
//        }
//    }


}
