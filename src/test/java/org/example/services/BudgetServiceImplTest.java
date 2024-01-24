package org.example.services;

import lombok.AllArgsConstructor;
import org.example.data.repository.BudgetRepository;
import org.example.data.repository.ExpenseRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.*;
import org.example.utils.EndDate;
import org.example.utils.StartDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BudgetServiceImplTest {
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ExpenseTrackerAppService expenseTrackerAppService;
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;

    @BeforeEach
    public void startAllBefore() {
        budgetRepository.deleteAll();
        expensesTrackerAppRepository.deleteAll();
    }

//    @Test
//    public void testThatRegisterUserCanSetBudget() {
//        RegisterRequest registerRequest = new RegisterRequest();
//        registerRequest.setPassword("Adewuyi@123");
//        registerRequest.setEmail("EmilyddAdewuyi12@gmail.com");
//        expenseTrackerAppService.register(registerRequest);
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("EmilyddAdewuyi12@gmail.com");
//        loginRequest.setPassword("Adewuyi@123");
//        expenseTrackerAppService.login(loginRequest);
//        AddIncomeRequest addIncomeRequest1 = new AddIncomeRequest();
//        addIncomeRequest1.setIncomeCategoryName("Family dues");
//        addIncomeRequest1.setAmount(5000);
//        addIncomeRequest1.setEmail("EmilyddAdewuyi12@gmail.com");
//        expenseTrackerAppService.addIncome(addIncomeRequest1);
//        AddIncomeRequest addIncomeRequest2 = new AddIncomeRequest();
//        addIncomeRequest2.setIncomeCategoryName("Family dues");
//        addIncomeRequest2.setAmount(1000);
//        addIncomeRequest2.setEmail("EmilyddAdewuyi12@gmail.com");
//        expenseTrackerAppService.addIncome(addIncomeRequest2);
//        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
//        addIncomeRequest.setIncomeCategoryName("Family dues");
//        addIncomeRequest.setAmount(2000);
//        addIncomeRequest.setEmail("EmilyddAdewuyi12@gmail.com");
//        expenseTrackerAppService.addIncome(addIncomeRequest);
//        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
//        addExpenseRequest.setExpenseCategoryName("food");
//        addExpenseRequest.setAmount(1000);
//        addExpenseRequest.setEmail("EmilyddAdewuyi12@gmail.com");
//        expenseTrackerAppService.addExpenses(addExpenseRequest);
//        StartDate startDate = new StartDate();
//        startDate.setDay("23");
//        startDate.setMonth("01");
//        startDate.setYear("2024");
//        EndDate endDate = new EndDate();
//        endDate.setDate("23");
//        endDate.setMonth("01");
//        endDate.setYear("2024");
//        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
//        setBudgetRequest.setEmail("EmilyddAdewuyi12@gmail.com");
//        setBudgetRequest.setAmount(2000);
//        setBudgetRequest.setStartDate(startDate);
//        setBudgetRequest.setEndDate(endDate);
//        assertNotNull(budgetService.setBudget(setBudgetRequest));
//        assertEquals(7000, expenseTrackerAppService.getBalance("EmilyddAdewuyi12@gmail.com"));
//
//    }

    @Test
    public void testThatRegisterUserCanSetBudgetAndGetBudgetBalance() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest1 = new AddIncomeRequest();
        addIncomeRequest1.setIncomeCategoryName("Pocket money");
        addIncomeRequest1.setAmount(8000);
        addIncomeRequest1.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest1);
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setExpenseCategoryName("Transport");
        addExpenseRequest.setAmount(1000);
        addExpenseRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        StartDate startDate = new StartDate();
        startDate.setDay("23");
        startDate.setMonth("01");
        startDate.setYear("2024");
        EndDate endDate = new EndDate();
        endDate.setDate("23");
        endDate.setMonth("01");
        endDate.setYear("2024");
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        setBudgetRequest.setAmount(2000);
        setBudgetRequest.setStartDate(startDate);
        setBudgetRequest.setEndDate(endDate);
        assertNotNull(budgetService.setBudget(setBudgetRequest));
        AddExpenseRequest addExpenseRequest1 = new AddExpenseRequest();
        addExpenseRequest1.setExpenseCategoryName("rent");
        addExpenseRequest1.setAmount(500);
        addExpenseRequest1.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest1);
        AddExpenseRequest addExpenseRequest2 = new AddExpenseRequest();
        addExpenseRequest2.setExpenseCategoryName("cloth");
        addExpenseRequest2.setAmount(1000);
        addExpenseRequest2.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest2);
        assertEquals(500,budgetService.getBudgetBalance("EmilyddAdewuyi12@gmail.com"));
        assertEquals(5500,expenseTrackerAppService.getBalance("EmilyddAdewuyi12@gmail.com"));


    }
}