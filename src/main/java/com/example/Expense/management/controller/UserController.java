package com.example.Expense.management.controller;

import com.example.Expense.management.JWT.UserUtil;
import com.example.Expense.management.dto.UserDto;
import com.example.Expense.management.dto.LoginDto;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.loginreponse.LoginMessage;
import com.example.Expense.management.sercurity.CustomUserDetails;
import com.example.Expense.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
//        try {
//            UserDto registeredUser = userService.registerUser(userDto);
//            return ResponseEntity.ok(registeredUser);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            Map<String, Object> response = userService.registerUser(userDto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        LoginMessage loginMesage = userService.LoginUser(loginDto);
        return ResponseEntity.ok(loginMesage);
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUser() {
        try {
//          CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//          User user = userDetails.getUser();
//          UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail());

            User currentUser = UserUtil.getCurrentUser();
            UserDto userDto = new UserDto(currentUser.getId(), currentUser.getUsername(), currentUser.getEmail());
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }

    @PutMapping("/update/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserDto userDto) {
        try {
//          CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//          User currentUser = userDetails.getUser();
//          User updatedUser = userService.updateProfile(currentUser.getId(), userDto.getUsername());
//          UserDto updatedUserDto = new UserDto(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());

            User currentUser = UserUtil.getCurrentUser();
            User updatedUser = userService.updateProfile(currentUser.getId(), userDto.getUsername());
            UserDto updatedUserDto = new UserDto(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());
            return ResponseEntity.ok(updatedUserDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
//            return ResponseEntity.status(401).body("Unauthorized");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}
