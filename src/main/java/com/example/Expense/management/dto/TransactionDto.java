package com.example.Expense.management.dto;

import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;

    private BigDecimal amount;

    private String note;

    private LocalDate date;

    private Category category;

    private User user;

}
