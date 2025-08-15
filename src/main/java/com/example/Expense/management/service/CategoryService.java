package com.example.Expense.management.service;

import com.example.Expense.management.dto.CategoryDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    boolean createCategory (CategoryDto categoryDto, User user);
}
