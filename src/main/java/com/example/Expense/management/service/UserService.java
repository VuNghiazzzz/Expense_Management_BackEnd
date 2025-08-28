package com.example.Expense.management.service;

import com.example.Expense.management.dto.UserDto;
import com.example.Expense.management.dto.LoginDto;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.loginreponse.LoginMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
//    UserDto registerUser(UserDto userDto);
    User findByUsername(String username);
    User getCurrentUser();
    LoginMessage LoginUser(LoginDto loginDto);
    Map<String, Object> registerUser(UserDto userDto);
}
