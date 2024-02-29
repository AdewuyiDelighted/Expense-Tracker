package org.example.services;

import org.example.data.model.*;
import org.example.data.repository.ExpensesTrackerAppRepository;
import org.example.data.repository.IncomeRepository;
import org.example.dto.request.AddIncomeRequest;
import org.example.exception.InvalidAmountException;
import org.example.exception.InvalidDetailsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.utils.Mapper.map;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private ExpenseTrackerAppService expenseTrackerAppService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ExpensesTrackerAppRepository expensesTrackerAppRepository;

    @Override
    public Income addIncome(AddIncomeRequest addIncomeRequest) {
        if (addIncomeRequest.getAmount() <= 0) throw new InvalidAmountException("Enter a valid amount");
        Optional<ExpensesTrackerApp> expensesTrackerApp = expensesTrackerAppRepository.findByEmail(addIncomeRequest.getEmail());
        if (expensesTrackerApp.isPresent()) {
            Income income = map(addIncomeRequest, expensesTrackerApp.get(), categoryService);
            incomeRepository.save(income);
            return income;
        }
        throw new InvalidDetailsException("Invalid detail");

    }

    @Override
    public List<Income> getAllIncomeBelongingTo(Long expenseTrackerId) {
        ArrayList<Income> allUserIncome = new ArrayList<>();
        for (Income income : incomeRepository.findAll()) {
            if (income.getExpensesTrackerApp().getId() == expenseTrackerId) {
                allUserIncome.add(income);
            }
        }
        return allUserIncome;
    }

}

