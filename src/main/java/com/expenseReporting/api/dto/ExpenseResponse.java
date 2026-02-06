package com.expenseReporting.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.expenseReporting.api.entity.enums.Category;
import com.expenseReporting.api.entity.enums.PaymentMethod;

public class ExpenseResponse {

    private String expenseId;
    private String title;
    private BigDecimal amount;
    private String currency;
    private Category category;
    private PaymentMethod paymentMethod;
    private LocalDate expenseDate;
    
    
	public String getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public LocalDate getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}
}
