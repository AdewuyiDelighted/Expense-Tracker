package org.example.data.repository;


import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesTrackerApp extends JpaRepository<ExpensesTrackerApp,Long> {


}
