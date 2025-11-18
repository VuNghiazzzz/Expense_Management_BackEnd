package com.example.Expense.management.dto;

import java.math.BigDecimal;

public class MonthlyStatDto {
    private int month;
    private BigDecimal income;
    private BigDecimal expense;

    public MonthlyStatDto(int month, BigDecimal income, BigDecimal expense) {
        this.month = month;
        this.income = income;
        this.expense = expense;
    }

    public int getMonth() {
        return month;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public BigDecimal getExpense() {
        return expense;
    }
}
