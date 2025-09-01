package com.example.Expense.management.controller;

import com.example.Expense.management.dto.CategoryDto;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.repository.CategoryRepository;
import com.example.Expense.management.sercurity.CustomUserDetails;
import com.example.Expense.management.service.CategoryService;
import com.example.Expense.management.service.UserService;
import com.example.Expense.management.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/categories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User currentUser = userService.getCurrentUser();
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        List<CategoryDto> categories = categoryService.getAllCategoriesByUser(user);
        if (categories != null){
            return ResponseEntity.ok(categories);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(categories);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> createCategory(@RequestBody CategoryDto categoryDto) {
//      User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//      User currentUser = userService.getCurrentUser();
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        System.out.println("Current User ID: " + (user != null ? user.getId() : "null"));
        boolean isSuccess = categoryService.createCategory(categoryDto, user);
        if(isSuccess){
            return ResponseEntity.ok(isSuccess);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isSuccess);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User currentUser = userService.getCurrentUser();
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        try {
            CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto, user);
            if (updatedCategory != null) {
                return ResponseEntity.ok(updatedCategory);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
            }
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User currentUser = userService.getCurrentUser();
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        try {
            boolean isDeleted = categoryService.deleteCategory(id, user);
            if (isDeleted) {
                return ResponseEntity.ok("Category deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
            }
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

}
