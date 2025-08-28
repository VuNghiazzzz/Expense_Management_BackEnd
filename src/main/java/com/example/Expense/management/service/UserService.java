package com.example.Expense.management.service;

import com.example.Expense.management.dto.UserDto;
import com.example.Expense.management.entity.LoginDto;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.loginreponse.LoginMesage;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto registerUser(UserDto userDto);
    User findByUsername(String username);
    User getCurrentUser();
    LoginMesage LoginUser(LoginDto loginDto);

}
