package com.example.Expense.management.service;

import com.example.Expense.management.dto.CategoryDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    boolean createCategory (CategoryDto categoryDto, User user);

    List<CategoryDto> getAllCategoriesByUser(User user);

    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto, User user);

    boolean deleteCategory(Long categoryId, User user);

    CategoryDto getCategoryById(Long categoryId, User user);
}
