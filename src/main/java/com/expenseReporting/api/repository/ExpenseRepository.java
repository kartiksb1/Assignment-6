package com.expenseReporting.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.expenseReporting.api.entity.Expense;

import java.time.LocalDate;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    Page<Expense> findByExpenseDateBetween(
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );
}