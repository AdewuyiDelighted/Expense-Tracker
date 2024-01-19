package org.example.services;

import jakarta.transaction.Transactional;
import org.example.data.model.Income;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.data.repository.IncomeRepository;
import org.example.dto.request.AddExpenseRequest;
import org.example.dto.request.AddIncomeRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exception.InvalidDetailsException;
import org.example.exception.UserAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
class ExpenseTrackerAppServiceImplTest {
    @Autowired
    private ExpenseTrackerAppService expenseTrackerAppService;
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    IncomeRepository incomeRepository;

    @Test
    public void testThatUserCanRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("Delighted@2211");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        assertEquals(1, expensesTrackerAppRepository.count());
    }

    @Test
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
        assertEquals(1,incomeRepository.count());
        assertEquals(4000,expenseTrackerAppService.getBalance("deborahdelighted5@gmail.com"));
    }
    @Test
    public void testThatUserCanRegisterAndLoginAndAddExpenseCheckBalance() {
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
        assertEquals(4000,expenseTrackerAppService.getBalance("deborahdelighted5@gmail.com"));
        AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
        addExpenseRequest.setExpenseCategoryName("Salary");
        addExpenseRequest.setAmount(2500);
        addExpenseRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.addExpenses(addExpenseRequest);
        assertEquals(1,incomeRepository.count());
        assertEquals(1500,expenseTrackerAppService.getBalance("deborahdelighted5@gmail.com"));
    }






}