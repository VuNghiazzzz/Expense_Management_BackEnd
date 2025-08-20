package com.example.Expense.management.repository;

import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.Transaction;
import com.example.Expense.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction, Long> {

    List<Transaction> findByUser(User user);

    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByUserAndDate(User user, LocalDate date);

    List<Transaction> findByUserAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByUserAndCategoryTypeAndDateBetween(User user, Category.CategoryType type, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByUserAndCategoryTypeAndDateBetween(Long userId, Category.CategoryType type, LocalDate startDate, LocalDate endDate);

    Optional<Transaction> findByIdAndUserId(Long transactionId, Long userId);

    List<Transaction> findByUserIdAndDate(Long userId, LocalDate date);

    Optional<BigDecimal> findTotalAmountByUserIdAndCategoryTypeAndDateBetween(
            @Param("userId") Long userId,
            @Param("type") Category.CategoryType type,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
