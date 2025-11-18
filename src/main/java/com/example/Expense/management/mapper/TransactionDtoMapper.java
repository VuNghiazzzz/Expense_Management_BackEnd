package com.example.Expense.management.mapper;

import com.example.Expense.management.dto.TransactionDto;
import com.example.Expense.management.entity.Transaction;

public class TransactionDtoMapper {
    public static TransactionDto transactionDto(Transaction transaction) {

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setId(transaction.getId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setNote(transaction.getNote());
        transactionDto.setUserId(transaction.getUser().getId());

        if (transaction.getCategory() != null) {
            transactionDto.setCategoryId(transaction.getCategory().getId());
            transactionDto.setCategoryName(transaction.getCategory().getName());
            transactionDto.setType(transaction.getCategory().getType().name());
        }

        return transactionDto;
    }

}
