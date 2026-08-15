package com.app.expensetracker.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ExpenseRequestDTO {


    @NotBlank(message = "Title cannot be empty")
    private String title;


    @Positive(message = "Amount must be greater than zero")
    private Double amount;


    @NotBlank(message = "Category is required")
    private String category;


    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;


    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}