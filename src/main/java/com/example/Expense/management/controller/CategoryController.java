package com.example.Expense.management.controller;

import com.example.Expense.management.dto.CategoryDto;
import com.example.Expense.management.entity.User;
import com.example.Expense.management.repository.CategoryRepository;
import com.example.Expense.management.service.impl.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryServiceImpl categoryServiceimpl;
    private CategoryRepository categoryRepository;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity createCategory(@RequestBody CategoryDto categoryDto) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isSuccess = this.categoryServiceimpl.createCategory(categoryDto, currentUser);
        if(isSuccess){
            return ResponseEntity.ok(isSuccess);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isSuccess);
        }
    }

}
