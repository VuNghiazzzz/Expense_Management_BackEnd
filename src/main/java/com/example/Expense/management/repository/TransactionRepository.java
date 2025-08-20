package com.example.Expense.management.repository;

import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.Transaction;
import com.example.Expense.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction, Long> {

    List<Transaction> findByUser(User user);

    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByUserAndDate(User user, LocalDate date);

    List<Transaction> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByUserAndCategoryTypeAndDateBetween(User user, Category.CategoryType type, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByUserAndCategoryTypeAndDateBetween(Long userId, Category.CategoryType type, LocalDate startDate, LocalDate endDate);

    Optional<Transaction> findByIdAndUserId(Long transactionId, Long userId);
}
