package com.example.Expense.management.service.impl;

import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.Transaction;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.exception.ResourceNotFoundException;
import com.example.Expense.management.mapper.TransactionMapper;
import com.example.Expense.management.repository.CategoryRepository;
import com.example.Expense.management.repository.TransactionRepository;
import com.example.Expense.management.repository.UserRepository;
import com.example.Expense.management.service.TransactionService;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TransactionImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private UserRepository userRepository;

    private CategoryRepository categoryRepository;

    public TransactionImpl(TransactionRepository transactionRepository,
                                  UserRepository userRepository,
                                  CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    @Transactional
    public TransactionDto createTransaction(Long userId, TransactionDto transactionDto) {
        // Find User by userId, if not found, throw an exception
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        // Find Category by CategoryId, if not found, throw an exception
        Category category = categoryRepository.findById(transactionDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + transactionDto.getCategoryId()));

        // Check Does the Category belong to the current User?
        if (!category.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Category does not belong to the authenticated user.");
        }

        // Convert DTO to Entity
        Transaction transaction = TransactionMapper.mapToTransaction(transactionDto, user, category);

        // Save Entity to the database
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Convert the saved Entity to a DTO and return it
        return TransactionMapper.mapToTransactionDto(savedTransaction);
    }

    @Override
    public TransactionDto getTransactionById(Long userId, Long transactionId) {
        // Find Transaction by transactionId
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + transactionId));

        // Make sure the transaction belongs to the current user
        if (!transaction.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Transaction not found for user with ID: " + userId);
        }
        return TransactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    public List<TransactionDto> getAllTransactionsByUser(Long userId) {
        return null;
    }

    @Override
    public TransactionDto updateTransaction(Long userId, Long transactionId, TransactionDto transactionDto) {
        return null;
    }

    @Override
    public void deleteTransaction(Long userId, Long transactionId) {

    }

    @Override
    public List<TransactionDto> getTransactionsByDate(Long userId, LocalDate date) {
        return null;
    }

    @Override
    public List<TransactionDto> getTransactionsBetweenDates(Long userId, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public BigDecimal getTotalIncome(Long userId, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public BigDecimal getTotalExpense(Long userId, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public Map<String, BigDecimal> getMonthlySummary(Long userId, int year) {
        return null;
    }

    @Override
    public Map<String, BigDecimal> getExpenseSummaryByCategory(Long userId, LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
