package com.example.Expense.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseManagementApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to the Expense API");
		SpringApplication.run(ExpenseManagementApplication.class, args);
	}
}
