package org.example.services;

import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exception.UserAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseTrackerAppServiceImplTest {
    @Autowired
    private ExpenseTrackerAppService expenseTrackerAppService;
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;

    @Test
    public void testThatUserCanRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Delighted");
        registerRequest.setPassword("Delighted@2211");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        assertEquals(1, expensesTrackerAppRepository.count());
    }
    @Test public void testThatTwoUserCantRegister(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Delighted");
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        RegisterRequest registerRequestQudus = new RegisterRequest();
        registerRequestQudus.setUsername("Delighted");
        registerRequestQudus.setEmail("deborahdelighted5@gmail.com");
        registerRequestQudus.setPassword("Adeshina@21");
        assertThrows(UserAlreadyExistException.class,()->expenseTrackerAppService.register(registerRequestQudus));
    }
    @Test public void testThatUserCanRegisterAndLogin(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Delighted");
        registerRequest.setPassword("Adewuyi@123");
        registerRequest.setEmail("deborahdelighted5@gmail.com");
        expenseTrackerAppService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(loginRequest.getEmail());
        loginRequest.setPassword(loginRequest.getPassword());
        expenseTrackerAppService.login(loginRequest);
        assertEquals(1,expensesTrackerAppRepository.count());

    }

}