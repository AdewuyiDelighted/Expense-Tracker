package org.example.services;


import org.example.data.repository.BudgetRepository;
import org.example.data.repository.ExpenseRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.data.repository.IncomeRepository;
import org.example.dto.request.AddExpenseRequest;
import org.example.dto.request.AddIncomeRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exception.InvalidAmountException;
import org.example.exception.InvalidDetailsException;
import org.example.exception.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "/test.properties")
//@Transactional
class ExpenseTrackerAppServiceImplTest {
    @Autowired
    private ExpenseTrackerAppService expenseTrackerAppService;
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    BudgetRepository budgetRepository;

    @BeforeEach
    public void startWith() {
        expensesTrackerAppRepository.deleteAll();
        budgetRepository.deleteAll();

        incomeRepository.deleteAll();
        expenseRepository.deleteAll();

    }

    @Test
    public void testThatUserCanRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Delighted@2211");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        assertEquals(1, expensesTrackerAppRepository.count());
    }

    @ Test
    public void testThatTwoUserCantRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        RegisterRequest registerRequestQudus = new RegisterRequest();
        registerRequestQudus.setEmail("deborahdelighted5@gmail.com");
        registerRequestQudus.setPassword("Adeshina@21");
        assertThrows(UserAlreadyExistException.class, () -> expenseTrackerAppService.register(registerRequestQudus));
    }

    @Test
    public void testThatUserCanRegisterAndLogin() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted5@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        assertEquals(1, expensesTrackerAppRepository.count());

    }

    @Test
    public void testThatUserCanRegisterAndLoginWithWrongPassword() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted5@gmail.com");
        loginRequest.setPassword("Adewuyi@1");
        assertThrows(InvalidDetailsException.class, () -> expenseTrackerAppService.login(loginRequest));

    }

    @Test
    public void testThatUserCanRegisterAndLoginWithWrongEmail() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        assertThrows(InvalidDetailsException.class, () -> expenseTrackerAppService.login(loginRequest));
    }

    @Test

    public void testThatUserCanRegisterAndLoginAndAddIncomeCheckBalance() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted5@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setIncomeCategoryName("Salary");
        addIncomeRequest.setAmount(4000);
        addIncomeRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        assertEquals(1, incomeRepository.count());
        assertEquals(4000, expenseTrackerAppService.getBalance("deborahdelighted5@gmail.com"));
    }

    @Test
    public void testThatUserCanRegisterAndLoginAndAddIncomeTwiceCheckBalance() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted5@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setIncomeCategoryName("Salary");
        addIncomeRequest.setAmount(4000);
        addIncomeRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        assertEquals(4000, expenseTrackerAppService.getBalance("deborahdelighted5@gmail.com"));
        AddIncomeRequest addIncomeRequest1 = new AddIncomeRequest();
        addIncomeRequest1.setIncomeCategoryName("My daddy dash me");
        addIncomeRequest1.setAmount(2500);
        addIncomeRequest1.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest1);
        assertEquals(2, incomeRepository.count());
        assertEquals(6500, expenseTrackerAppService.getBalance("deborahdelighted5@gmail.com"));
    }

    @Test
    public void testThatUserCanRegisterAndLoginAndAddExpenseCheckBalance() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted6@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted6@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setIncomeCategoryName("Salary");
        addIncomeRequest.setAmount(4000);
        addIncomeRequest.setEmail("deborahdelighted6@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddIncomeRequest addIncomeRequest1 = new AddIncomeRequest();
        addIncomeRequest1.setIncomeCategoryName("Salary");
        addIncomeRequest1.setAmount(4000);
        addIncomeRequest1.setEmail("deborahdelighted6@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest1);
        assertEquals(8000, expenseTrackerAppService.getBalance("deborahdelighted6@gmail.com"));
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setExpenseCategoryName("Home");
        addExpenseRequest.setAmount(2000);
        addExpenseRequest.setEmail("deborahdelighted6@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        assertEquals(2, incomeRepository.count());
        assertEquals(1, expenseRepository.count());
        assertEquals(6000, expenseTrackerAppService.getBalance("deborahdelighted6@gmail.com"));

    }

    @Test
    public void testThatUserCanRegisterAndLoginAndAddThreeIncomeAndAddTwoExpenseCheckBalance() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted5@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setIncomeCategoryName("Salary");
        addIncomeRequest.setAmount(4000);
        addIncomeRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddIncomeRequest addIncomeRequest1 = new AddIncomeRequest();
        addIncomeRequest1.setIncomeCategoryName("My daddy dash me");
        addIncomeRequest1.setAmount(4000);
        addIncomeRequest1.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest1);
        assertEquals(8000, expenseTrackerAppService.getBalance("deborahdelighted5@gmail.com"));
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setExpenseCategoryName("food");
        addExpenseRequest.setAmount(2000);
        addExpenseRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        assertEquals(6000, expenseTrackerAppService.getBalance("deborahdelighted5@gmail.com"));
        AddExpenseRequest addExpenseRequest1 = new AddExpenseRequest();
        addExpenseRequest1.setExpenseCategoryName("rent");
        addExpenseRequest1.setAmount(1000);
        addExpenseRequest1.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest1);
        assertEquals(2, incomeRepository.count());
        assertEquals(2, expenseRepository.count());
        assertEquals(5000, expenseTrackerAppService.getBalance("deborahdelighted5@gmail.com"));
    }

    @Test
    public void testThatUserCanRegisterAndLoginAndInvalidAmount() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted5@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setIncomeCategoryName("extra money");
        addIncomeRequest.setAmount(0);
        addIncomeRequest.setEmail("deborahdelighted5@gmail.com");
        assertThrows(InvalidAmountException.class, () -> expenseTrackerAppService.addIncome(addIncomeRequest));

    }



    @Test
    public void testThatUserCanRegisterAndLoginAndIncomeAmountAndAddExpenseFindAllExpense() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted51@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted51@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setIncomeCategoryName("Family dues");
        addIncomeRequest.setAmount(8000);
        addIncomeRequest.setEmail("deborahdelighted51@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setExpenseCategoryName("food");
        addExpenseRequest.setAmount(1000);
        addExpenseRequest.setEmail("deborahdelighted51@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        AddExpenseRequest addExpenseRequest1 = new AddExpenseRequest();
        addExpenseRequest1.setExpenseCategoryName("food");
        addExpenseRequest1.setAmount(4000);
        addExpenseRequest1.setEmail("deborahdelighted51@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest1);
        AddExpenseRequest addExpenseRequest2 = new AddExpenseRequest();
        addExpenseRequest2.setExpenseCategoryName("food");
        addExpenseRequest2.setAmount(3000);
        addExpenseRequest2.setEmail("deborahdelighted51@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest2);
        assertEquals(3, expenseTrackerAppService.findAllExpenseBelongingTo("deborahdelighted51@gmail.com").size());
    }

    @Test
    public void testThatUserCanRegisterAndLoginAndIncomeAmountAndFindAllIncome() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted51@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("deborahdelighted51@gmail.com");
        loginRequest.setPassword("Adewuyi@123");
        expenseTrackerAppService.login(loginRequest);
        AddIncomeRequest addIncomeRequest = new AddIncomeRequest();
        addIncomeRequest.setIncomeCategoryName("Family dues");
        addIncomeRequest.setAmount(8000);
        addIncomeRequest.setEmail("deborahdelighted51@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest);
        AddIncomeRequest addIncomeRequest1 = new AddIncomeRequest();
        addIncomeRequest1.setIncomeCategoryName("Family dues");
        addIncomeRequest1.setAmount(2000);
        addIncomeRequest1.setEmail("deborahdelighted51@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest1);
        AddIncomeRequest addIncomeRequest2 = new AddIncomeRequest();
        addIncomeRequest2.setIncomeCategoryName("Family dues");
        addIncomeRequest2.setAmount(2000);
        addIncomeRequest2.setEmail("deborahdelighted51@gmail.com");
        expenseTrackerAppService.addIncome(addIncomeRequest2);
        assertEquals(3, expenseTrackerAppService.findAllIncomeBelongingTo("deborahdelighted51@gmail.com").size());
    }



}