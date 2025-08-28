package com.example.Expense.management.controller;

import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.service.TransactionService;
import com.example.Expense.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //Get UserId from JWT Principal
    private Long getCurrentUserId(Principal principal) {
        return Long.parseLong(principal.getName());
    }

    @PostMapping("/add")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto dto, Principal principal) {
        Long userId = getCurrentUserId(principal);
        return ResponseEntity.ok(transactionService.createTransaction(userId, dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(Principal principal) {
        Long userId = getCurrentUserId(principal);
        return ResponseEntity.ok(transactionService.getAllTransactionsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id, Principal principal) {
        Long userId = getCurrentUserId(principal);
        return ResponseEntity.ok(transactionService.getTransactionById(userId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto dto, Principal principal) {
        Long userId = getCurrentUserId(principal);
        return ResponseEntity.ok(transactionService.updateTransaction(userId, id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id, Principal principal) {
        Long userId = getCurrentUserId(principal);
        transactionService.deleteTransaction(userId, id);
        return ResponseEntity.noContent().build();
    }

    //Take Sumary Monthly
    @GetMapping("/summary/monthly")
    public ResponseEntity<Map<String, BigDecimal>> getMonthlySummary(@RequestParam int year, Principal principal) {
        Long userId = getCurrentUserId(principal);
        Map<String, BigDecimal> summary = transactionService.getMonthlySummary(userId, year);
        return ResponseEntity.ok(summary);
    }


}
