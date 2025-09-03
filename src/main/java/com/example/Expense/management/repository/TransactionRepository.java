package com.example.Expense.management.repository;

import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.Transaction;
import com.example.Expense.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction, Long> {

    List<Transaction> findByUserId(Long user);

    List<Transaction> findByUserIdAndDate(Long userId, LocalDate date);

    List<Transaction> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    Optional<Transaction> findByIdAndUserId(Long transactionId, Long userId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.date = :date")
    BigDecimal findSumAmountByUserIdAndDate(Long userId, LocalDate date);

}
