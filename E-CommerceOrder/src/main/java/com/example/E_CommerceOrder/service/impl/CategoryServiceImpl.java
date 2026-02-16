package com.example.E_CommerceOrder.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.CategoryRequestDto;
import com.example.E_CommerceOrder.entity.Category;
import com.example.E_CommerceOrder.repository.CategoryRepo;
import com.example.E_CommerceOrder.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category addCategory(CategoryRequestDto dto) {

        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());

        return categoryRepo.save(category);
    }
    
//    / ✅ VIEW ALL CATEGORIES
    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    // ✅ UPDATE CATEGORY
    @Override
    public Category updateCategory(Integer id, CategoryRequestDto dto) {

        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());

        return categoryRepo.save(category);
    }

    // ✅ DELETE CATEGORY
    @Override
    public void deleteCategory(Integer id) {

        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepo.delete(category);
    }
    
    
}