package com.example.Expense.management.repository;

import com.example.Expense.management.dto.CategorySummaryDto;
import com.example.Expense.management.dto.MonthlyStatDto;
import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.Transaction;
import com.example.Expense.management.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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



    // summary monthly
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.user.id = :userId AND t.category.type = 'INCOME' " +
            "AND YEAR(t.date) = :year AND MONTH(t.date) = :month")
    BigDecimal sumMonthlyIncome(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.user.id = :userId AND t.category.type = 'EXPENSE' " +
            "AND YEAR(t.date) = :year AND MONTH(t.date) = :month")
    BigDecimal sumMonthlyExpense(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);


    // monthly stat for 12 month
    @Query("SELECT new com.example.Expense.management.dto.summary.MonthlyStatDto( " +
            "MONTH(t.date), " +
            "SUM(CASE WHEN c.type = 'INCOME' THEN t.amount ELSE 0 END), " +
            "SUM(CASE WHEN c.type = 'EXPENSE' THEN t.amount ELSE 0 END)) " +
            "FROM Transaction t JOIN t.category c " +
            "WHERE t.user.id = :userId AND YEAR(t.date) = :year " +
            "GROUP BY MONTH(t.date) " +
            "ORDER BY MONTH(t.date)")
    List<MonthlyStatDto> getMonthlyStats(@Param("userId") Long userId, @Param("year") int year);


    // filter pagination
    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId "
            + "AND (:type IS NULL OR t.category.type = :type) "
            + "AND (:categoryId IS NULL OR t.category.id = :categoryId) "
            + "AND (:startDate IS NULL OR t.date >= :startDate) "
            + "AND (:endDate IS NULL OR t.date <= :endDate)"
    )
    Page<Transaction> filterTransactions(@Param("userId") Long userId, @Param("type") String type, @Param("categoryId") Long categoryId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );


    // summary category
    @Query("SELECT new com.example.Expense.management.dto.CategorySummaryDto(" +
            "t.category.name, SUM(t.amount)) " +
            "FROM Transaction t " +
            "WHERE t.user.id = :userId " +
            "AND YEAR(t.date) = :year " +
            "AND MONTH(t.date) = :month " +
            "GROUP BY t.category.name")
    List<CategorySummaryDto> sumByCategory(@Param("userId") Long userId,
                                           @Param("year") int year,
                                           @Param("month") int month);

}
