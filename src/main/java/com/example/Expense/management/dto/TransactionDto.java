package com.example.Expense.management.dto;

import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


public class TransactionDto {
    private Long id;

    private BigDecimal amount;

    private String note;

    private LocalDate date;

    private Long categoryId;

    private Long userId;

    public TransactionDto() {
    }

    public TransactionDto(Long id, BigDecimal amount, String note, LocalDate date, Long categoryId, Long userId) {
        this.id = id;
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
