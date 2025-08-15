package com.example.Expense.management.dto;

import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
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
