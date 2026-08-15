package com.app.expensetracker.service;

import com.app.expensetracker.mapper.ExpenseMapper;
import com.app.expensetracker.model.Expense;
import com.app.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import com.app.expensetracker.dto.ExpenseRequestDTO;
import com.app.expensetracker.dto.ExpenseResponseDTO;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResponseDTO saveExpense(ExpenseRequestDTO expenseDTO) {

        Expense expense = ExpenseMapper.toEntity(expenseDTO);

        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseMapper.toDTO(savedExpense);
    }

    @Override
    public List<ExpenseResponseDTO> getAllExpenses() {

        return expenseRepository.findAll()
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    @Override
    public ExpenseResponseDTO getExpenseById(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElse(null);

        if(expense == null){
            return null;
        }

        return ExpenseMapper.toDTO(expense);
    }
    @Override
    public ExpenseResponseDTO updateExpense(Long id,
                                            ExpenseRequestDTO expenseDTO) {

        Expense existingExpense =
                expenseRepository.findById(id)
                        .orElse(null);

        if(existingExpense == null){
            return null;
        }


        existingExpense.setTitle(expenseDTO.getTitle());
        existingExpense.setAmount(expenseDTO.getAmount());
        existingExpense.setCategory(expenseDTO.getCategory());
        existingExpense.setExpenseDate(expenseDTO.getExpenseDate());
        existingExpense.setDescription(expenseDTO.getDescription());


        Expense updatedExpense =
                expenseRepository.save(existingExpense);


        return ExpenseMapper.toDTO(updatedExpense);
    }
    @Override
    public boolean deleteExpense(Long id) {

        if (!expenseRepository.existsById(id)) {
            return false;
        }

        expenseRepository.deleteById(id);

        return true;
    }
    @Override
    public List<ExpenseResponseDTO> getExpensesByCategory(String category){

        return expenseRepository.findByCategory(category)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();

    }



    @Override
    public List<ExpenseResponseDTO> searchExpenses(String keyword){

        return expenseRepository
                .findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();

    }
}