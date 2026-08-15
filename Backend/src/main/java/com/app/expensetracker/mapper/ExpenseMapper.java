package com.app.expensetracker.mapper;

import com.app.expensetracker.dto.ExpenseRequestDTO;
import com.app.expensetracker.dto.ExpenseResponseDTO;
import com.app.expensetracker.model.Expense;

public class ExpenseMapper {

    public static Expense toEntity(ExpenseRequestDTO dto) {

        Expense expense = new Expense();

        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setDescription(dto.getDescription());

        return expense;
    }


    public static ExpenseResponseDTO toDTO(Expense expense) {

        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getExpenseDate(),
                expense.getDescription()
        );
    }
}