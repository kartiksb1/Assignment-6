package com.expenseReporting.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v2/expenses")
@Tag(
    name = "Expense Management v2",
    description = "Version 2 of Expense APIs with contract improvements"
)
public class ExpenseControllerV2 {

    private final ExpenseService expenseService;

    public ExpenseControllerV2(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Operation(
            summary = "Add a new expense (v2)",
            description = "Same as v1 but versioned for future contract changes"
    )
    @PostMapping
    public ExpenseCreateResponse addExpenseV2(
            @Valid @RequestBody ExpenseCreateRequest request
    ) {
        return expenseService.createExpense(request);
    }

    @Operation(
            summary = "Get expenses by date range (v2)",
            description = "Paginated expenses with stable v2 contract"
    )
    @GetMapping
    public PageResponse<ExpenseResponse> getExpensesByDateRangeV2(

            @Parameter(example = "2026-02-01")
            @RequestParam LocalDate startDate,

            @Parameter(example = "2026-02-06")
            @RequestParam LocalDate endDate,

            @Parameter(example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(example = "10")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(example = "expenseDate,desc")
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
