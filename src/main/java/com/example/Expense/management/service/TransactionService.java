package com.example.Expense.management.service;

import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.entity.Transaction;
import com.example.Expense.management.entity.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface TransactionService {

    boolean createTransaction(Long userId, TransactionDto transactionDto);

    TransactionDto getTransactionById(Long userId, Long transactionId);

    List<TransactionDto> getAllTransactionsByUser(Long userId);

    TransactionDto updateTransaction(Long userId, Long transactionId, TransactionDto transactionDto);

    boolean deleteTransaction(Long userId, Long transactionId);

    List<TransactionDto> getTransactionsByDate(Long userId, LocalDate date);

    List<TransactionDto> getTransactionsBetweenDates(Long userId, LocalDate startDate, LocalDate endDate);

    Map<String, BigDecimal> getMonthlySummary(Long userId, int year);

    BigDecimal getDailyTotal(Long userId, int year, int month, int day);

//    Map<String, BigDecimal> getExpenseSummaryByCategory(Long userId, LocalDate startDate, LocalDate endDate);

}

