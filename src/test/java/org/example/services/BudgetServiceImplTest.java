package org.example.services;


import org.example.data.repository.BudgetRepository;
import org.example.data.repository.CategoryRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.*;
import org.example.exception.InvalidBudgetAmountException;
import org.example.exception.InvalidDateException;
import org.example.exception.InvalidDateFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "/test.properties")
//@Transactional
class BudgetServiceImplTest {
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private ExpenseTrackerAppService expenseTrackerAppService;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        expensesTrackerAppRepository.deleteAll();
        budgetRepository.deleteAll();
        categoryRepository.deleteAll();
    }


    @Test
    public void testThatUserCanRegisterLonginAndSetBudgetThatAParticularPeriodOfTime() {
        expensesTrackerAppRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("delightedEmily@gmail.com");
        registerRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("delightedEmily@gmail.com");
        loginRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount(5000);
        addIncomeRequest.setEmail("delightedEmily@gmail.com");
        addIncomeRequest.setIncomeCategoryName("Salary");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setEmail("delightedEmily@gmail.com");
        addExpenseRequest.setAmount(2500);
        addExpenseRequest.setExpenseCategoryName("House Rent");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("delightedEmily@gmail.com");
        setBudgetRequest.setAmount(2000);
        setBudgetRequest.setStartDate(LocalDate.now().getDayOfMonth());
        setBudgetRequest.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setStartYear(LocalDate.now().getYear());
        setBudgetRequest.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setEndYear(LocalDate.now().getYear());
        assertEquals(1, expensesTrackerAppRepository.count());
        assertNotNull(budgetService.setBudget(setBudgetRequest));
        assertEquals(1, budgetRepository.count());
    }

    @Test
    public void testThatUserCanRegisterLoginAndSetBudgetThatAParticularPeriodOfTimeButCantInputInvlidDate() {
        expensesTrackerAppRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("delightedEmily@gmail.com");
        registerRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("delightedEmily@gmail.com");
        loginRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount(5000);
        addIncomeRequest.setEmail("delightedEmily@gmail.com");
        addIncomeRequest.setIncomeCategoryName("Salary");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setEmail("delightedEmily@gmail.com");
        addExpenseRequest.setAmount(2500);
        addExpenseRequest.setExpenseCategoryName("House Rent");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("delightedEmily@gmail.com");
        setBudgetRequest.setAmount(2000);
        setBudgetRequest.setStartDate(31);
        setBudgetRequest.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setStartYear(LocalDate.now().getYear());
        setBudgetRequest.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setEndYear(LocalDate.now().getYear());
        assertThrows(InvalidDateFormatException.class, () -> budgetService.setBudget(setBudgetRequest));
    }

    @Test
    public void testThatUserCanRegisterLoginAndSetBudgetThatAParticularPeriodOfTimeButCanInputDateThatIsNotBeforeTheCurrentDate() {
        expensesTrackerAppRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("delightedEmily@gmail.com");
        registerRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("delightedEmily@gmail.com");
        loginRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount(5000);
        addIncomeRequest.setEmail("delightedEmily@gmail.com");
        addIncomeRequest.setIncomeCategoryName("Salary");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setEmail("delightedEmily@gmail.com");
        addExpenseRequest.setAmount(2500);
        addExpenseRequest.setExpenseCategoryName("House Rent");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("delightedEmily@gmail.com");
        setBudgetRequest.setAmount(2000);
        setBudgetRequest.setStartDate(1);
        setBudgetRequest.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setStartYear(LocalDate.now().getYear());
        setBudgetRequest.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setEndYear(LocalDate.now().getYear());
        assertThrows(InvalidDateException.class, () -> budgetService.setBudget(setBudgetRequest));
    }

    @Test
    public void testThatUserCanRegisterLoginAndSetBudgetsFindARecentBudget() {
        expensesTrackerAppRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("delightedebby@gmail.com");
        registerRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("delightedebby@gmail.com");
        loginRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount(5000);
        addIncomeRequest.setEmail("delightedebby@gmail.com");
        addIncomeRequest.setIncomeCategoryName("Salary");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setEmail("delightedebby@gmail.com");
        addExpenseRequest.setAmount(2500);
        addExpenseRequest.setExpenseCategoryName("House Rent");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("delightedebby@gmail.com");
        setBudgetRequest.setAmount(500);
        setBudgetRequest.setStartDate(LocalDate.now().getDayOfMonth());
        setBudgetRequest.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setStartYear(LocalDate.now().getYear());
        setBudgetRequest.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setEndYear(LocalDate.now().getYear());
        budgetService.setBudget(setBudgetRequest);
        SetBudgetRequest setBudgetRequest1 = new SetBudgetRequest();
        setBudgetRequest1.setEmail("delightedebby@gmail.com");
        setBudgetRequest1.setAmount(2500);
        setBudgetRequest1.setStartDate(LocalDate.now().getDayOfMonth());
        setBudgetRequest1.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest1.setStartYear(LocalDate.now().getYear());
        setBudgetRequest1.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest1.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest1.setEndYear(LocalDate.now().getYear());
        budgetService.setBudget(setBudgetRequest1);
        assertEquals(2500, budgetService.findARecentBudget("delightedebby@gmail.com").getBudgetAmount());
        assertEquals(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()), budgetService.findARecentBudget("delightedebby@gmail.com").getStartDate());
    }

    @Test
    public void testThatUserCanRegisterLoginAndSetBudgetsFindAllBudget() {
        expensesTrackerAppRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("delightedebby@gmail.com");
        registerRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("delightedebby@gmail.com");
        loginRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount(5000);
        addIncomeRequest.setEmail("delightedebby@gmail.com");
        addIncomeRequest.setIncomeCategoryName("Salary");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setEmail("delightedebby@gmail.com");
        addExpenseRequest.setAmount(500);
        addExpenseRequest.setExpenseCategoryName("House Rent");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        System.out.println(expenseTrackerAppService.getBalance("delightedebby@gmail.com"));
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("delightedebby@gmail.com");
        setBudgetRequest.setAmount(4000);
        setBudgetRequest.setStartDate(LocalDate.now().getDayOfMonth());
        setBudgetRequest.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setStartYear(LocalDate.now().getYear());
        setBudgetRequest.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setEndYear(LocalDate.now().getYear());
        budgetService.setBudget(setBudgetRequest);
        SetBudgetRequest setBudgetRequest1 = new SetBudgetRequest();
        setBudgetRequest1.setEmail("delightedebby@gmail.com");
        setBudgetRequest1.setAmount(3000);
        setBudgetRequest1.setStartDate(LocalDate.now().getDayOfMonth());
        setBudgetRequest1.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest1.setStartYear(LocalDate.now().getYear());
        setBudgetRequest1.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest1.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest1.setEndYear(LocalDate.now().getYear());
        budgetService.setBudget(setBudgetRequest1);
        SetBudgetRequest setBudgetRequest2 = new SetBudgetRequest();
        setBudgetRequest2.setEmail("delightedebby@gmail.com");
        setBudgetRequest2.setAmount(3000);
        setBudgetRequest2.setStartDate(LocalDate.now().getDayOfMonth());
        setBudgetRequest2.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest2.setStartYear(LocalDate.now().getYear());
        setBudgetRequest2.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest2.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest2.setEndYear(LocalDate.now().getYear());
        budgetService.setBudget(setBudgetRequest2);
        assertEquals(3, budgetService.findAllBudgetBelongingTo("delightedebby@gmail.com").size());
    }

    @Test
    public void testThatUserCanRegisterLoginAndSetBudgetsGetTheRecentBudgetBalance() {
        expensesTrackerAppRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("delightedebby@gmail.com");
        registerRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("delightedebby@gmail.com");
        loginRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount(15000);
        addIncomeRequest.setEmail("delightedebby@gmail.com");
        addIncomeRequest.setIncomeCategoryName("Salary");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setEmail("delightedebby@gmail.com");
        addExpenseRequest.setAmount(1000);
        addExpenseRequest.setExpenseCategoryName("House Rent");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("delightedebby@gmail.com");
        setBudgetRequest.setAmount(6000);
        setBudgetRequest.setStartDate(LocalDate.now().getDayOfMonth());
        setBudgetRequest.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setStartYear(LocalDate.now().getYear());
        setBudgetRequest.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setEndYear(LocalDate.now().getYear());
        budgetService.setBudget(setBudgetRequest);
        AddExpenseRequest addExpenseRequest1 = new AddExpenseRequest();
        addExpenseRequest1.setExpenseCategoryName("Ope food");
        addExpenseRequest1.setAmount(2000);
        addExpenseRequest1.setEmail("delightedebby@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest1);
        AddExpenseRequest addExpenseRequest2 = new AddExpenseRequest();
        addExpenseRequest2.setExpenseCategoryName("Ope food");
        addExpenseRequest2.setAmount(2000);
        addExpenseRequest2.setEmail("delightedebby@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest2);
        assertEquals(2000, budgetService.getBudgetBalance("delightedebby@gmail.com"));
    }

    @Test
    public void testThatUserCanRegisterLoginAndWhenUserWantInputAmountGreaterThanExpenseAppBalance() {
        expensesTrackerAppRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("delightedUs@gmail.com");
        registerRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("delightedUs@gmail.com");
        loginRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount(10000);
        addIncomeRequest.setEmail("delightedUs@gmail.com");
        addIncomeRequest.setIncomeCategoryName("Salary");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("delightedUs@gmail.com");
        setBudgetRequest.setAmount(12000);
        setBudgetRequest.setStartDate(LocalDate.now().getDayOfMonth());
        setBudgetRequest.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setStartYear(LocalDate.now().getYear());
        setBudgetRequest.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setEndYear(LocalDate.now().getYear());
        assertThrows(InvalidBudgetAmountException.class, () -> budgetService.setBudget(setBudgetRequest));
    }

    @Test
    public void testThatUserCanRegisterLoginAndWhenUserSetBudgetAndWouldGetNegativeBalanceAfterIfExpenseIsMore() {
        expensesTrackerAppRepository.deleteAll();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("delightedUs@gmail.com");
        registerRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("delightedUs@gmail.com");
        loginRequest.setPassword("JesusDebby@21");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setAmount(10000);
        addIncomeRequest.setEmail("delightedUs@gmail.com");
        addIncomeRequest.setIncomeCategoryName("Salary");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        SetBudgetRequest setBudgetRequest = new SetBudgetRequest();
        setBudgetRequest.setEmail("delightedUs@gmail.com");
        setBudgetRequest.setAmount(8000);
        setBudgetRequest.setStartDate(LocalDate.now().getDayOfMonth());
        setBudgetRequest.setStartMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setStartYear(LocalDate.now().getYear());
        setBudgetRequest.setEndDate(LocalDate.now().getDayOfMonth() + 1);
        setBudgetRequest.setEndMonth(LocalDate.now().getMonthValue());
        setBudgetRequest.setEndYear(LocalDate.now().getYear());
        AddExpenseRequest addExpenseRequest1 = new AddExpenseRequest();
        addExpenseRequest1.setExpenseCategoryName("Ope food");
        addExpenseRequest1.setAmount(3000);
        addExpenseRequest1.setEmail("delightedUs@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest1);
        AddExpenseRequest addExpenseRequest2 = new AddExpenseRequest();
        addExpenseRequest2.setExpenseCategoryName("Ope food");
        addExpenseRequest2.setAmount(5000);
        addExpenseRequest2.setEmail("delightedUs@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest2);
        AddExpenseRequest addExpenseRequest3 = new AddExpenseRequest();
        addExpenseRequest3.setExpenseCategoryName("Ope food");
        addExpenseRequest3.setAmount(3500);
        addExpenseRequest3.setEmail("delightedUs@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest3);
        assertEquals(-1500, expenseTrackerAppService.getBalance("delightedUs@gmail.com"));
    }
}