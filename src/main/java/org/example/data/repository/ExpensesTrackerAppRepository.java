package org.example.data.repository;


import org.example.data.model.ExpensesTrackerApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpensesTrackerAppRepository extends JpaRepository<ExpensesTrackerApp,Long> {
   Optional<ExpensesTrackerApp> findByEmail(String email);
  Optional<ExpensesTrackerApp> findById(Long expensesTrackerId);
}
