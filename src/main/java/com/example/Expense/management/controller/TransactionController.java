package com.example.Expense.management.controller;

import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.sercurity.CustomUserDetails;
import com.example.Expense.management.service.TransactionService;
import com.example.Expense.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //Get UserId from JWT Principal
//    private Long getCurrentUserId(Principal principal) {
//        return Long.parseLong(principal.getName());
//    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> createTransaction(@RequestBody TransactionDto dto ) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        boolean isSuccess = transactionService.createTransaction(user.getId(), dto);
        if (isSuccess) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return ResponseEntity.ok(transactionService.getAllTransactionsByUser(user.getId()));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return ResponseEntity.ok(transactionService.getTransactionById(user.getId(), id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto dto) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        return ResponseEntity.ok(transactionService.updateTransaction(user.getId(), id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTransaction(@PathVariable Long id) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        boolean isSuccess = transactionService.deleteTransaction(user.getId(), id);
        if (isSuccess) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    //Take total Sumary
    @GetMapping("/summary/daily")
    public ResponseEntity<BigDecimal> getDailySummary(@RequestParam int year, @RequestParam int month, @RequestParam int day)
    {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        BigDecimal total = transactionService.getDailyTotal(user.getId(), year, month, day);
        return ResponseEntity.ok(total);
    }

    //Take details Sumary
    @GetMapping("/details/daily")
    public ResponseEntity<List<TransactionDto>> getDailyTransactionDetails(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        LocalDate date = LocalDate.of(year, month, day);
        List<TransactionDto> transactions = transactionService.getTransactionsByDate(user.getId(), date);
        return ResponseEntity.ok(transactions);
    }

}
