package com.expenseReporting.api.controller;


import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.expenseReporting.api.dto.ExpenseCreateRequest;
import com.expenseReporting.api.dto.ExpenseCreateResponse;
import com.expenseReporting.api.dto.ExpenseResponse;
import com.expenseReporting.api.dto.PageResponse;
import com.expenseReporting.api.service.ExpenseService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseControllerV1 {

    private final ExpenseService expenseService;

    public ExpenseControllerV1(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseCreateResponse addExpense(
            @Valid @RequestBody ExpenseCreateRequest request
    ) {
        return expenseService.createExpense(request);
    }

    @GetMapping
    public PageResponse<ExpenseResponse> getExpensesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "expenseDate,desc") String sort
    ) {
        String[] sortParams = sort.split(",");
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0])
        );

        return expenseService.getExpenses(startDate, endDate, pageRequest);
    }
}
