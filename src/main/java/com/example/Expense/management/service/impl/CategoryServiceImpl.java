package com.example.Expense.management.service.impl;

import com.example.Expense.management.dto.CategoryDto;
import com.example.Expense.management.entity.Category;
import com.example.Expense.management.entity.User;
//import com.example.Expense.management.mapper.CategoryMapper;
import com.example.Expense.management.mapper.CategoryMapper;
import com.example.Expense.management.repository.CategoryRepository;
import com.example.Expense.management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public boolean createCategory(CategoryDto categoryDto, User user) {
//        user = new User();
//        user.setId(categoryDto.getUserId());
//        Category category = new Category();
//        category.setId(categoryDto.getId());
//        category.setName(categoryDto.getName());
//        category.setType(categoryDto.getType());
//        category.setUser(user);
//        Category saveCategory = categoryRepository.save(category);
        Category category = CategoryMapper.mapToCategory(categoryDto,user);
        Category SaveCategory = categoryRepository.save(category);
        return SaveCategory !=null;
    }
}
