package com.example.Expense.management.dto;

import java.math.BigDecimal;

public class CategorySummaryDto {
    private String categoryName;
    private BigDecimal totalAmount;

    public CategorySummaryDto(String categoryName, BigDecimal totalAmount) {
        this.categoryName = categoryName;
        this.totalAmount = totalAmount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
