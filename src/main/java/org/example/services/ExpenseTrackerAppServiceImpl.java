package org.example.services;

import org.example.data.model.ExpensesTrackerApp;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exception.InvalidEmailFormatException;
import org.example.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.example.utils.Mapper.map;
import static org.example.utils.Verification.passwordChecker;
import static org.example.utils.Verification.validateEmail;

@Service
public class ExpenseTrackerAppServiceImpl implements ExpenseTrackerAppService {
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;

    @Override
    public void register(RegisterRequest registerRequest) {
        if (isRegistered(registerRequest.getUsername(), registerRequest.getEmail()))throw new UserAlreadyExistException("User already exist!!!");
        passwordChecker(registerRequest);bcrypt(registerRequest);
        if(!validateEmail(registerRequest.getEmail()))throw new InvalidEmailFormatException("Invalid email format");
        ExpensesTrackerApp expensesTrackerApp = map(registerRequest);
        expensesTrackerAppRepository.save(expensesTrackerApp);

    }

    @Override
    public void login(LoginRequest loginRequest) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();



    }

    private static void bcrypt(RegisterRequest registerRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        registerRequest.setPassword(encodedPassword);
    }

    private boolean isRegistered(String username, String email) {
        for (ExpensesTrackerApp expensesTrackerApp : expensesTrackerAppRepository.findAll()) {
            if (expensesTrackerApp.getUsername().equals(username) && expensesTrackerApp.getEmail().equals(email))
                return true;
        }
        return false;
    }
}
