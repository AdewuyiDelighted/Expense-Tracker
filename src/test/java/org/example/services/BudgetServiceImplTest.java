package org.example.services;

import org.example.data.model.Budget;
import org.example.data.repository.BudgetRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.*;
import org.example.utils.EndDate;
import org.example.utils.StartDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    public void testThatRegisterUserCanSetBudget() {
        budgetRepository.deleteAll();
        expensesTrackerAppRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest1 = new AddIncomeRequest();
        addIncomeRequest1.setIncomeCategoryName("Family dues");
        addIncomeRequest1.setAmount(5000);
        addIncomeRequest1.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest1);
        AddIncomeRequest addIncomeRequest2 = new AddIncomeRequest();
        addIncomeRequest2.setIncomeCategoryName("Family dues");
        addIncomeRequest2.setAmount(1000);
        addIncomeRequest2.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest2);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setIncomeCategoryName("Family dues");
        addIncomeRequest.setAmount(2000);
        addIncomeRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setExpenseCategoryName("food");
        addExpenseRequest.setAmount(1000);
        addExpenseRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        StartDate startDate = new StartDate();
        startDate.setDay("31");
        startDate.setMonth("01");
        startDate.setYear("2024");
        EndDate endDate = new EndDate();
        endDate.setDate("31");
        endDate.setMonth("01");
        endDate.setYear("2024");
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        setBudgetRequest.setAmount(2000);
        setBudgetRequest.setStartDate(startDate);
        setBudgetRequest.setEndDate(endDate);
        assertNotNull(budgetService.setBudget(setBudgetRequest));
        assertEquals(7000, expenseTrackerAppService.getBalance("EmilyddAdewuyi12@gmail.com"));

    }

    @Test
    public void testThatRegisterUserCanSetBudgetAndGetBudgetBalance() {
        budgetRepository.deleteAll();
        expensesTrackerAppRepository.deleteAll();
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
        startDate.setDay("30");
        startDate.setMonth("01");
        startDate.setYear("2024");
        EndDate endDate = new EndDate();
        endDate.setDate("30");
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
        assertEquals(-500, budgetService.getBudgetBalance("EmilyddAdewuyi12@gmail.com"));
        assertEquals(5500, expenseTrackerAppService.getBalance("EmilyddAdewuyi12@gmail.com"));
    }

    @Test
    public void testThatRegisterUserCanSetBudgetAndFindTheLastBudgetAccount() {
        budgetRepository.deleteAll();
        expensesTrackerAppRepository.deleteAll();
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
        StartDate startDate = new StartDate();
        startDate.setDay("30");
        startDate.setMonth("01");
        startDate.setYear("2024");
        EndDate endDate = new EndDate();
        endDate.setDate("30");
        endDate.setMonth("01");
        endDate.setYear("2024");
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("EmilyddAdewuyi12@gmail.com");
        setBudgetRequest.setAmount(2000);
        setBudgetRequest.setStartDate(startDate);
        setBudgetRequest.setEndDate(endDate);
        budgetService.setBudget(setBudgetRequest);
        Budget budget = budgetService.findBudget("EmilyddAdewuyi12@gmail.com");
        assertEquals(2000, budget.getBudgetAmount());
        assertEquals(8000, expenseTrackerAppService.getBalance("EmilyddAdewuyi12@gmail.com"));
    }

    @Test
    public void testThatRegisterUserCanSetBudgetAndFindAllBudgetUserHad() {
        budgetRepository.deleteAll();
        expensesTrackerAppRepository.deleteAll();
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
        StartDate startDate1 = new StartDate();
        startDate1.setDay("31");
        startDate1.setMonth("01");
        startDate1.setYear("2024");
        EndDate endDate1 = new EndDate();
        endDate1.setDate("31");
        endDate1.setMonth("01");
        endDate1.setYear("2024");
        SetBudgetRequest setBudgetRequest1 = new SetBudgetRequest();
        setBudgetRequest1.setEmail("EmilyddAdewuyi12@gmail.com");
        setBudgetRequest1.setAmount(2000);
        setBudgetRequest1.setStartDate(startDate1);
        setBudgetRequest1.setEndDate(endDate1);
        budgetService.setBudget(setBudgetRequest1);
        List<Budget> userBudget = budgetService.findAllBudget("EmilyddAdewuyi12@gmail.com");
        assertEquals(1, userBudget.size());
        assertEquals(8000, expenseTrackerAppService.getBalance("EmilyddAdewuyi12@gmail.com"));
    }

    @Test
    public void testThatRegisterUserCanSetBudgetAndEndAExistingBudget() {
        budgetRepository.deleteAll();
        expensesTrackerAppRepository.deleteAll();
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
        StartDate startDate1 = new StartDate();
        startDate1.setDay("30");
        startDate1.setMonth("01");
        startDate1.setYear("2024");
        EndDate endDate1 = new EndDate();
        endDate1.setDate("30");
        endDate1.setMonth("01");
        endDate1.setYear("2024");
        SetBudgetRequest setBudgetRequest1 = new SetBudgetRequest();
        setBudgetRequest1.setEmail("EmilyddAdewuyi12@gmail.com");
        setBudgetRequest1.setAmount(2000);
        setBudgetRequest1.setStartDate(startDate1);
        setBudgetRequest1.setEndDate(endDate1);
        budgetService.setBudget(setBudgetRequest1);
        budgetService.endBudget("EmilyddAdewuyi12@gmail.com");
        StartDate startDate2 = new StartDate();
        startDate2.setDay("30");
        startDate2.setMonth("01");
        startDate2.setYear("2024");
        EndDate endDate2 = new EndDate();
        endDate2.setDate("30");
        endDate2.setMonth("01");
        endDate2.setYear("2024");
        SetBudgetRequest setBudgetRequest2 = new SetBudgetRequest();
        setBudgetRequest2.setEmail("EmilyddAdewuyi12@gmail.com");
        setBudgetRequest2.setAmount(2000);
        setBudgetRequest2.setStartDate(startDate1);
        setBudgetRequest2.setEndDate(endDate1);
        assertTrue(budgetService.setBudget(setBudgetRequest2).isActive());
        assertFalse(budgetService.endBudget("EmilyddAdewuyi12@gmail.com").isActive());
        List<Budget> userBudget = budgetService.findAllBudget("EmilyddAdewuyi12@gmail.com");
        assertEquals(2, userBudget.size());
        assertEquals(8000, expenseTrackerAppService.getBalance("EmilyddAdewuyi12@gmail.com"));
    }


}