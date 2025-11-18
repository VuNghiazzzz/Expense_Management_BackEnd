package com.example.Expense.management.service;

import com.example.Expense.management.dto.CategorySummaryDto;
import com.example.Expense.management.dto.MonthlyStatDto;
import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.dto.TransactionSummaryDto;
import com.example.Expense.management.entity.Transaction;
import com.example.Expense.management.entity.User;
import org.springframework.data.domain.Page;
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

    TransactionDto updateTransactionCategory(Long userId, Long transactionId, Long categoryId);


    // Summary, Statistics
    TransactionSummaryDto getMonthlySummary(Long userId, int year, int month);

    TransactionSummaryDto getYearlySummary(Long userId, int year);

    List<CategorySummaryDto> getSummaryByCategory(Long userId, int year, int month);

    List<MonthlyStatDto> getMonthlyStats(Long userId, int year);

    //Filtering, Pagination
    Page<TransactionDto> filterTransactions(Long userId, String type, Long categoryId,
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size
    );


}

