package com.app.expensetracker.service;

import com.app.expensetracker.model.Expense;
import com.app.expensetracker.dto.ExpenseRequestDTO;
import com.app.expensetracker.dto.ExpenseResponseDTO;

import java.util.List;

public interface ExpenseService {

    ExpenseResponseDTO saveExpense(ExpenseRequestDTO expenseDTO);

    List<ExpenseResponseDTO> getAllExpenses();
    ExpenseResponseDTO getExpenseById(Long id);
    ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO expenseDTO);
    boolean deleteExpense(Long id);

    List<ExpenseResponseDTO> getExpensesByCategory(String category);

    List<ExpenseResponseDTO> searchExpenses(String keyword);
}