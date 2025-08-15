package com.example.Expense.management.dto;

import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
