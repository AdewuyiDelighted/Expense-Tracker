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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.utils.Mapper.map;


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
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(setBudgetRequest.getEmail());
        if (expensesTrackerApp.isPresent()) {
            validateAmount(setBudgetRequest, expensesTrackerApp);
            Budget budget = map(setBudgetRequest, expensesTrackerApp.get());
            if (!validateLastBudgetStatus(setBudgetRequest.getEmail(), budget)) {
                expensesTrackerApp.get().setActiveBudget(true);
                expensesTrackerAppRepository.save(expensesTrackerApp.get());
                budgetRepository.save(budget);
                emailService.emailSender(setBudgetRequest.getEmail(), """
                        Welcome Note""", """
                        Dear User,
                        You have successfully set a budget for a particular duration of time
                        Here is your detail regard your new budget""" + budget);
                return budget;
            }
        }
        throw new InvalidDetailsException("Enter a valid details !!!!!!!!");
    }


    private static void validateAmount(SetBudgetRequest setBudgetRequest, Optional<ExpensesTrackerApp> expensesTrackerApp) {
        if (setBudgetRequest.getAmount() <= 0) throw new InvalidBudgetAmountException("Enter a valid amount !!!!");
        if (setBudgetRequest.getAmount() > expensesTrackerApp.get().getBalance())
            throw new InvalidBudgetAmountException("Account Balance is too low for budget amount!!!!");
    }

    @Override
    public double getBudgetBalance(String mail) {
        double totalExpenses = 0;
        Budget budget = findARecentBudget(mail);
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(mail);
        if (expensesTrackerApp.isPresent()) {
            for (Expense expense : expenseService.getAllExpenseBelongingTo(expensesTrackerApp.get().getId())) {
                if (expense.getDateAdded().isBefore(budget.getEndDate()) && expense.isBudgetActive()) {
                    totalExpenses += expense.getAmount();
                }

            }
            budget.setBudgetBalance(budget.getBudgetBalance() - totalExpenses);
            return budget.getBudgetBalance();
        }
        throw new InvalidDetailsException("Details Invalid");
    }

    @Override
    public Budget findARecentBudget(String mail) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(mail);
        if (expensesTrackerApp.isPresent()) {
            return lastElementOfUserBudget(findAllBudgetBelongingTo(mail));


        }
        throw new InvalidDetailsException("Enter a valid details");
    }

    @Override
    public List<Budget> findAllBudgetBelongingTo(String email) {
        ArrayList<Budget> listOfBudget = new ArrayList<>();
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(email);
        if (expensesTrackerApp.isPresent()) {
            for (Budget budget : budgetRepository.findAll()) {
                if (budget.getExpenseAppTrackerId().equals(expensesTrackerApp.get().getId())) listOfBudget.add(budget);
            }
        }
        return listOfBudget;
    }

    @Override
    public Budget endBudget(String mail) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(mail);
        if (expensesTrackerApp.isPresent()) {
            expensesTrackerApp.get().setActiveBudget(false);
            expensesTrackerAppRepository.save(expensesTrackerApp.get());
            Budget budget = findARecentBudget(mail);
            if (budget != null) {
                budget.setActive(false);
                budgetRepository.save(budget);
                return budget;
            }
            throw new NoExisingBudgetExistingException("No existing budget found");
        }
        throw new InvalidDetailsException("Invalid details!!!!! TRY AGAIN");

    }

    @Override
    public Budget resetBudget(ResetBudgetRequest resetBudgetRequest) {
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(resetBudgetRequest.getEmail());
        if (expensesTrackerApp.isPresent()) {
            Budget budget = findARecentBudget(resetBudgetRequest.getEmail());
            budget.setBudgetBalance(getBudgetBalance(resetBudgetRequest.getEmail()) + resetBudgetRequest.getNewAmount());
            budget.setEndDate(LocalDate.of(resetBudgetRequest.getNewEndYear(), resetBudgetRequest.getNewEndMonth(), resetBudgetRequest.getNewEndDate()));
            budgetRepository.save(budget);
            return budget;
        }
        throw new InvalidDetailsException("Enter a valid details !!!");
    }


    @Override
    @Scheduled(cron = "0 5 0 * * *")
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

//    private ExpensesTrackerApp findAnAccount(Dtring )
}




