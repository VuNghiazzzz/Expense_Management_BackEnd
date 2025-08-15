package com.example.Expense.management.dto;

import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.Transaction;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Transaction> transactions;
    private List<Category> categories;
}
