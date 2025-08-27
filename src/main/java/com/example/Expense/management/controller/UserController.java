package com.example.Expense.management.controller;

import com.example.Expense.management.dto.UserDto;
import com.example.Expense.management.entity.LoginDto;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.loginreponse.LoginMesage;
import com.example.Expense.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            UserDto registeredUser = userService.registerUser(userDto);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        LoginMesage loginMesage = userService.LoginUser(loginDto);
        return ResponseEntity.ok(loginMesage);
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return ResponseEntity.ok(user);
    }
}
