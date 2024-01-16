package org.example.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Expense extends JpaRepository<Expense,Long> {
}
