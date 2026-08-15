package com.app.expensetracker.controller;

import com.app.expensetracker.model.Expense;
import com.app.expensetracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.app.expensetracker.dto.ExpenseRequestDTO;
import com.app.expensetracker.dto.ExpenseResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> addExpense(
            @Valid @RequestBody ExpenseRequestDTO expenseDTO
    ){

        ExpenseResponseDTO savedExpense =
                expenseService.saveExpense(expenseDTO);

        return new ResponseEntity<>(
                savedExpense,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses(){

        List<ExpenseResponseDTO> expenses =
                expenseService.getAllExpenses();

        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(
            @PathVariable Long id){

        ExpenseResponseDTO expense =
                expenseService.getExpenseById(id);

        if(expense == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequestDTO expenseDTO
    ){

        ExpenseResponseDTO updatedExpense =
                expenseService.updateExpense(id, expenseDTO);

        if(updatedExpense == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable Long id){

        boolean deleted =
                expenseService.deleteExpense(id);

        if(!deleted){
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(
                "Expense deleted successfully"
        );
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExpenseResponseDTO>>
    getByCategory(
            @PathVariable String category){

        return ResponseEntity.ok(
                expenseService.getExpensesByCategory(category)
        );

    }
    @GetMapping("/search")
    public ResponseEntity<List<ExpenseResponseDTO>>
    search(
            @RequestParam String keyword){

        return ResponseEntity.ok(
                expenseService.searchExpenses(keyword)
        );

    }
}