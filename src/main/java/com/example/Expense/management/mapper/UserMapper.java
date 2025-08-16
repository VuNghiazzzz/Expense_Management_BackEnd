package com.example.Expense.management.mapper;

import com.example.Expense.management.dto.CategoryDto;
import com.example.Expense.management.dto.UserDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.User;

public class UserMapper {
    public static User mapUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto mapUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
