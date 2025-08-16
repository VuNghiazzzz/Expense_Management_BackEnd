package com.example.Expense.management.service;

import com.example.Expense.management.dto.UserDto;
import com.example.Expense.management.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto registerUser(UserDto userDto);
    User findByUsername(String username);
    User getCurrentUser();
}
