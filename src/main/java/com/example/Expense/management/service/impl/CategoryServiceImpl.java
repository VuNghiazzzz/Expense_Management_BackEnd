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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<CategoryDto> getAllCategoriesByUser(User user) {
        List<Category> categories = categoryRepository.findByUser(user);
        return categories.stream()
                .map(CategoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto, User user) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                if (!category.getUser().getId().equals(user.getId())) { // Only User update
                    throw new RuntimeException("You don't have permission to delete this category");
                }
                category.setName(categoryDto.getName());
                category.setType(categoryDto.getType());
                Category updatedCategory = categoryRepository.save(category);
                return CategoryMapper.mapToCategoryDto(updatedCategory);
            }
            return null; // throw NotFoundException
        }


    @Override
    public boolean deleteCategory(Long categoryId, User user) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            if (!category.getUser().getId().equals(user.getId())) { // Only User update
                throw new RuntimeException("You don't have permission to delete this category.");
            }
            // Because there is ON DELETE SET NULL in the DB, the transactions
            // related will not be lost, but only assigned category_id=NULL
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }


}
