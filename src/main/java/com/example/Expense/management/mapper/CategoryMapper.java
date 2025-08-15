package com.example.Expense.management.mapper;

import com.example.Expense.management.dto.CategoryDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.User;

public class CategoryMapper {
    public static Category mapToCategory(CategoryDto categoryDto, User user){
            Category category = new Category();
            category.setId(categoryDto.getId());
            category.setType(categoryDto.getType());
            category.setName(categoryDto.getName());
            category.setUser(user);
            return category;
    }

    public static CategoryDto mapToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setType(category.getType());
        categoryDto.setName(category.getName());
        categoryDto.setUserId(category.getUser() != null ? category.getUser().getId() : null);
        return categoryDto;
    }

}
