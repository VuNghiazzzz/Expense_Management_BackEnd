package com.example.Expense.management.mapper;

import com.example.Expense.management.dto.CategoryDto;
import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.Transaction;
import com.example.Expense.management.entity.User;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto, User user, Category category) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setNote(transactionDto.getNote());
        transaction.setDate(transactionDto.getDate());
        transaction.setUser(user);
        transaction.setCategory(category);
        return transaction;
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setNote(transaction.getNote());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setUserId(transaction.getUser() != null ? transaction.getUser().getId() : null);
        transactionDto.setCategoryId(transaction.getCategory() != null ? transaction.getCategory().getId() : null);
        return transactionDto;
    }
}
