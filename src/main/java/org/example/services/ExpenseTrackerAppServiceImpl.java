package org.example.services;

import jakarta.transaction.Transactional;
import org.example.data.model.Expense;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.model.Income;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.AddExpenseRequest;
import org.example.dto.request.AddIncomeRequest;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.exception.AppLockedException;
import org.example.exception.InvalidDetailsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.utils.Mapper.map;
import static org.example.utils.Verification.*;

@Service
@Transactional
public class ExpenseTrackerAppServiceImpl implements ExpenseTrackerAppService {
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private ExpenseService expenseService;


    @Override
    public void register(RegisterRequest registerRequest) {
        validateNewUserInfo(registerRequest, expensesTrackerAppRepository);
        ExpensesTrackerApp expensesTrackerApp = map(registerRequest);
        expensesTrackerAppRepository.save(expensesTrackerApp);
    }

    @Override
    public void login(LoginRequest loginRequest) {
        ExpensesTrackerApp expensesTrackerApp = findAccount(loginRequest.getEmail());
        validateEmailAndPassword(loginRequest, expensesTrackerApp);
        expensesTrackerApp.setLocked(false);
        expensesTrackerAppRepository.save(expensesTrackerApp);
    }


    public Optional<ExpensesTrackerApp> findById(Long expensesTrackerId) {
        return expensesTrackerAppRepository.findById(expensesTrackerId);
    }

    @Override
    public void addIncome(AddIncomeRequest addIncomeRequest) {
        appUnlocked(addIncomeRequest.getEmail());
        ExpensesTrackerApp expensesTrackerApp = findAccount(addIncomeRequest.getEmail());
        Income income = incomeService.addIncome(addIncomeRequest);
        expensesTrackerApp.getUserIncome().add(income);
        expensesTrackerApp.setBalance(expensesTrackerApp.getBalance() + expensesTrackerApp.getUserIncome().getLast().getAmount());
        expensesTrackerAppRepository.save(expensesTrackerApp);
    }


    @Override
    public void addExpenses(AddExpenseRequest addExpenseRequest) {
        appUnlocked(addExpenseRequest.getEmail());
        ExpensesTrackerApp expensesTrackerApp = findAccount(addExpenseRequest.getEmail());
        Expense expense = expenseService.addExpenses(addExpenseRequest);
        expensesTrackerApp.getUserExpense().add(expense);
        expensesTrackerApp.setBalance(expensesTrackerApp.getBalance() - expense.getAmount());
        expensesTrackerAppRepository.save(expensesTrackerApp);
    }

    @Override
    public List<Expense> findAllExpenseBelongingTo(String email) {
        ExpensesTrackerApp expensesTrackerApp = findAccount(email);
        return expensesTrackerApp.getUserExpense();
    }

    @Override
    public List<Income> findAllIncomeBelongingTo(String email) {
        appUnlocked(email);
        ExpensesTrackerApp expensesTrackerApp = findAccount(email);
        return expensesTrackerApp.getUserIncome();
    }

    @Override
    public double getBalance(String email) {
        appUnlocked(email);
        ExpensesTrackerApp expensesTrackerApp = findAccount(email);
        return expensesTrackerApp.getBalance();
    }
    @Override
    public List<Object> findHistory(String email) {
        appUnlocked(email);
        ExpensesTrackerApp expensesTrackerApp = findAccount(email);
        ArrayList<Object> userHistory = new ArrayList<>();
        userHistory.addAll(findAllIncomeBelongingTo(email));
        userHistory.addAll(findAllExpenseBelongingTo(email));

        return userHistory;
    }


    private ExpensesTrackerApp findAccount(String email){
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(email);
        if (expensesTrackerApp.isPresent()) {
            return expensesTrackerApp.get();
        }

        throw new InvalidDetailsException("Invalid details");
    }

    private void appUnlocked(String email) {
        ExpensesTrackerApp expensesTrackerApp = findAccount(email);
        if (expensesTrackerApp.isLocked()) throw new AppLockedException("Unlock app ");
    }



}
