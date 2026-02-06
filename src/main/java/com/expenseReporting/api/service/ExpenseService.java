package com.expenseReporting.api.service;

import org.springframework.data.domain.Pageable;

import com.expenseReporting.api.dto.ExpenseCreateRequest;
import com.expenseReporting.api.dto.ExpenseCreateResponse;
import com.expenseReporting.api.dto.ExpenseResponse;
import com.expenseReporting.api.dto.PageResponse;

import java.time.LocalDate;

public interface ExpenseService {

    ExpenseCreateResponse createExpense(ExpenseCreateRequest request);

    PageResponse<ExpenseResponse> getExpenses(
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    );
}
