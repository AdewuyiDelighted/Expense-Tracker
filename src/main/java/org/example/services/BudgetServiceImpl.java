package org.example.services;


import org.example.data.model.Budget;
import org.example.data.model.Expense;
import org.example.data.model.ExpensesTrackerApp;
import org.example.data.repository.BudgetRepository;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.dto.request.ResetBudgetRequest;
import org.example.dto.request.SetBudgetRequest;
import org.example.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.utils.Mapper.map;
import static org.example.utils.Verification.validateAmount;


@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private EmailService emailService;

    @Override
    public Budget setBudget(SetBudgetRequest setBudgetRequest) {
        appUnlocked(setBudgetRequest.getEmail());
        ExpensesTrackerApp expensesTrackerApp = findAccount(setBudgetRequest.getEmail());
        validateAmount(setBudgetRequest, expensesTrackerApp);
        Budget budget = map(setBudgetRequest, expensesTrackerApp);
        if (!validateLastBudgetStatus(setBudgetRequest.getEmail(), budget)) {
            expensesTrackerApp.setActiveBudget(true);
            expensesTrackerAppRepository.save(expensesTrackerApp);
            budgetRepository.save(budget);
            emailService.emailSender(setBudgetRequest.getEmail(), """
                    Welcome Note""", """
                    Dear User,
                    You have successfully set a budget for a particular duration of time
                    Here is your detail regard your new budget""" + budget);
        }
        return budget;
    }


    @Override
    public double getBudgetBalance(String mail) {
        appUnlocked(mail);
        double totalExpenses = 0;
        Budget budget = findARecentBudget(mail);
        ExpensesTrackerApp expensesTrackerApp = findAccount(mail);
        for (Expense expense : expenseService.getAllExpenseBelongingTo(expensesTrackerApp.getId())) {
            if (expense.getDateAdded().isBefore(budget.getEndDate()) && expense.isBudgetIsActive()) {
                totalExpenses += expense.getAmount();
            }
        }
        budget.setBudgetBalance(budget.getBudgetBalance() - totalExpenses);
        return budget.getBudgetBalance();
    }

    @Override
    public Budget findARecentBudget(String mail) {
        appUnlocked(mail);
        ExpensesTrackerApp expensesTrackerApp = findAccount(mail);
        return lastElementOfUserBudget(findAllBudgetBelongingTo(mail));
    }

    @Override
    public List<Budget> findAllBudgetBelongingTo(String email) {
        appUnlocked(email);
        ArrayList<Budget> listOfBudget = new ArrayList<>();
        ExpensesTrackerApp expensesTrackerApp = findAccount(email);
        for (Budget budget : budgetRepository.findAll()) {
            if (budget.getExpenseAppTrackerId().equals(expensesTrackerApp.getId())) listOfBudget.add(budget);
        }
        return listOfBudget;
    }

    @Override
    public Budget endBudget(String mail) {
        appUnlocked(mail);
        ExpensesTrackerApp expensesTrackerApp = findAccount(mail);
        expensesTrackerApp.setActiveBudget(false);
        expensesTrackerAppRepository.save(expensesTrackerApp);
        Budget budget = findARecentBudget(mail);
        if (budget != null) {
            budget.setActive(false);
            budgetRepository.save(budget);
            return budget;
        }
        throw new NoExisingBudgetExistingException("No existing budget found");
    }

    @Override
    public Budget resetBudget(ResetBudgetRequest resetBudgetRequest) {
        appUnlocked(resetBudgetRequest.getEmail());
        ExpensesTrackerApp expensesTrackerApp = findAccount(resetBudgetRequest.getEmail());
        Budget budget = findARecentBudget(resetBudgetRequest.getEmail());
        budget.setBudgetBalance(getBudgetBalance(resetBudgetRequest.getEmail()) + resetBudgetRequest.getNewAmount());
        budget.setEndDate(LocalDate.of(resetBudgetRequest.getNewEndYear(), resetBudgetRequest.getNewEndMonth(), resetBudgetRequest.getNewEndDate()));
        budgetRepository.save(budget);
        return budget;
    }


    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void checkEndingDate() {
        for (Budget budget : budgetRepository.findAll()) {
            if (budget.getEndDate().isEqual(LocalDate.now())) {
                budget.setActive(false);
                budgetRepository.save(budget);
                Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findById(budget.getExpenseAppTrackerId());
                emailService.emailSender(expensesTrackerApp.get().getEmail(), """
                        Budget DueTime Reminder""", """
                        This is a reminder to get you notify on the due date of your budget
                        """ + budget);

            }
        }
    }


    private Budget lastElementOfUserBudget(List<Budget> listOfBudget) {
        if (!listOfBudget.isEmpty()) {
            Budget budget = listOfBudget.getLast();
            if (budget.isActive()) {
                return budget;
            }
        }
        return null;
    }


    private boolean validateLastBudgetStatus(String mail, Budget newBudget) {
        Budget budget = findARecentBudget(mail);
        if (budget != null) {
            if (budget.isActive() || newBudget.getStartDate().isBefore(budget.getEndDate())) {
                throw new BudgetCanNotBeEnableException("Budget cant be enable because of existing budget (end exising budget or reset budget )");
            }
        }
        return false;
    }

    private ExpensesTrackerApp findAccount(String email) {
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




