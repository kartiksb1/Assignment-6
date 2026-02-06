package com.expenseReporting.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.expenseReporting.api.dto.ExpenseCreateRequest;
import com.expenseReporting.api.dto.ExpenseCreateResponse;
import com.expenseReporting.api.dto.ExpenseResponse;
import com.expenseReporting.api.dto.PageResponse;
import com.expenseReporting.api.entity.Expense;
import com.expenseReporting.api.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseCreateResponse createExpense(ExpenseCreateRequest request) {

        Expense expense = new Expense();
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCurrency(request.getCurrency());
        expense.setCategory(request.getCategory());
        expense.setPaymentMethod(request.getPaymentMethod());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setNotes(request.getNotes());

        Expense saved = expenseRepository.save(expense);

        ExpenseCreateResponse response = new ExpenseCreateResponse();
        response.setExpenseId(saved.getId().toString());
        response.setStatus("CREATED");
        response.setCreatedAt(saved.getCreatedAt());

        return response;
    }

    @Override
    public PageResponse<ExpenseResponse> getExpenses(
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable
    ) {
        Page<Expense> page = expenseRepository
                .findByExpenseDateBetween(startDate, endDate, pageable);

        PageResponse<ExpenseResponse> response = new PageResponse<>();
        response.setCurrentPage(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setData(
                page.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList())
        );

        return response;
    }

    private ExpenseResponse mapToResponse(Expense expense) {
        ExpenseResponse dto = new ExpenseResponse();
        dto.setExpenseId(expense.getId().toString());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setCurrency(expense.getCurrency());
        dto.setCategory(expense.getCategory());
        dto.setPaymentMethod(expense.getPaymentMethod());
        dto.setExpenseDate(expense.getExpenseDate());
        return dto;
    }
}
