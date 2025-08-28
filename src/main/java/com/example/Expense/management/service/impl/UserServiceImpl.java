package com.example.Expense.management.service.impl;

import com.example.Expense.management.JWT.JwtUtil;
import com.example.Expense.management.dto.UserDto;
import com.example.Expense.management.dto.LoginDto;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.loginreponse.LoginMessage;
import org.springframework.security.core.Authentication;
import com.example.Expense.management.mapper.UserMapper;
import com.example.Expense.management.repository.UserRepository;
import com.example.Expense.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        // Check user already exists
        if (userRepository.findByUsername(userDto.getUsername()) != null || userRepository.findByEmail(userDto.getEmail()) != null) {
              throw new RuntimeException("A username or email that already exists.");
        }
        User user = UserMapper.mapUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return UserMapper.mapUserDto(savedUser); // Use a separate mapper to avoid password exposure
        }



    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
                return null;
            }
            return (User) authentication.getPrincipal(); // Getting the user from the context of Spring Security
    }

    //Login
    @Override
    public LoginMessage LoginUser(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getEmail());
        if (user == null || !user.getPassword().equals(loginDto.getPassword())) {
            return new LoginMessage("Invalid credentials", false, null);
        }

//        boolean passwordMatch = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
//        if (!passwordMatch) {
//            return new LoginMessage("Invalid credentials", false);
//        }

        String token = jwtUtil.generateToken(user.getEmail());

//      return new LoginMessage("Login successful", true);

        return new LoginMessage("Login successful", true, token);
    }


}
