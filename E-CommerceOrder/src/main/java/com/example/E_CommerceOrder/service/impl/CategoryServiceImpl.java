package com.example.E_CommerceOrder.service.impl;

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
}