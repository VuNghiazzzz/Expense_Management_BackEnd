package com.example.Expense.management.dto;

import com.example.Expense.management.entity.Category.CategoryType;
import com.example.Expense.management.entity.User;
import lombok.*;


public class CategoryDto {
    private Long id;
    private String name;
    private CategoryType type;
    private Long userId;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name, CategoryType type, Long userId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
