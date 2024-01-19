package org.example.services;

import org.example.data.model.ExpensesTrackerApp;
import org.example.data.model.Income;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.data.repository.IncomeRepository;
import org.example.dto.request.AddIncomeRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exception.InvalidDetailsException;
import org.example.exception.InvalidEmailFormatException;
import org.example.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.example.utils.Mapper.map;
import static org.example.utils.Verification.passwordChecker;
import static org.example.utils.Verification.validateEmail;

@Service
public class ExpenseTrackerAppServiceImpl implements ExpenseTrackerAppService {
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    IncomeService incomeService;

    @Override
    public void register(RegisterRequest registerRequest) {
        validateNewUserInfo(registerRequest);
        ExpensesTrackerApp expensesTrackerApp = map(registerRequest);
        expensesTrackerAppRepository.save(expensesTrackerApp);

    }

    @Override
    public void login(LoginRequest loginRequest) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(loginRequest.getEmail());
        validateEmailAndPassword(loginRequest, expensesTrackerApp);
        expensesTrackerApp.get().setLocked(false);
        expensesTrackerAppRepository.save(expensesTrackerApp.get());
    }


    public Optional<ExpensesTrackerApp> findById(Long expensesTrackerId) {
        return expensesTrackerAppRepository.findById(expensesTrackerId);
    }

    @Override
    public void addIncome(AddIncomeRequest addIncomeRequest) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(addIncomeRequest.getEmail());
        if (expensesTrackerApp.isPresent()) {
            ExpensesTrackerApp withoutOptionalExpenseTracker = expensesTrackerApp.get();
            incomeService.addIncome(addIncomeRequest.getIncomeCategoryName(), addIncomeRequest.getAmount(), withoutOptionalExpenseTracker.getId());
            for (Income income : incomeRepository.findAll()) {
                if (income.getExpensesTrackerApp().getId().equals(withoutOptionalExpenseTracker.getId())) {
                    withoutOptionalExpenseTracker.setBalance(withoutOptionalExpenseTracker.getBalance() + income.getAmount());
                    expensesTrackerAppRepository.save(withoutOptionalExpenseTracker);
                }
            }
        }

    }

    @Override
    public double getBalance(String email) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(email);
        return expensesTrackerApp.get().getBalance();
    }

//    @Override
//    public void addIncome(Income income) {
//        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findById(income.getExpensesTrackerApp().getId());
//        if (expensesTrackerApp.isPresent()) {
//            ExpensesTrackerApp withoutOptionalExpenseTracker = expensesTrackerApp.get();
//            withoutOptionalExpenseTracker.setBalance(withoutOptionalExpenseTracker.getBalance() + income.getAmount());
//            expensesTrackerAppRepository.save(withoutOptionalExpenseTracker);
//
//        }
//    }


        private boolean isRegistered (String email){
            for (ExpensesTrackerApp expensesTrackerApp : expensesTrackerAppRepository.findAll()) {
                if (expensesTrackerApp.getEmail().equals(email))
                    return true;
            }
            return false;
        }

        private static void bcrypt (RegisterRequest registerRequest){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
            registerRequest.setPassword(encodedPassword);
        }

        private void validateNewUserInfo (RegisterRequest registerRequest){
            if (isRegistered(registerRequest.getEmail()))
                throw new UserAlreadyExistException("User already exist!!!");
            passwordChecker(registerRequest);
            bcrypt(registerRequest);
            if (!validateEmail(registerRequest.getEmail()))
                throw new InvalidEmailFormatException("Invalid email format");
        }

        private static void validateEmailAndPassword (LoginRequest
        loginRequest, Optional < ExpensesTrackerApp > expensesTrackerApp){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (expensesTrackerApp.isEmpty()) throw new InvalidDetailsException("Detail invalid");
            if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), expensesTrackerApp.get().getPassword()))
                throw new InvalidDetailsException("Invalid details");
        }


    }
