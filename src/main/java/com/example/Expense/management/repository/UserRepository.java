package com.example.Expense.management.repository;

import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
