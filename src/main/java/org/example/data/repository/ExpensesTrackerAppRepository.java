package org.example.data.repository;


import org.example.data.model.ExpensesTrackerApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesTrackerAppRepository extends JpaRepository<ExpensesTrackerApp,Long> {
    ExpensesTrackerApp findByEmail(String email);


}
