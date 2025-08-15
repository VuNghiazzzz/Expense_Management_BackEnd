package com.example.Expense.management.dto;


import com.example.Expense.management.entity.CategoryType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private CategoryType type;
    private Long userId;
}
