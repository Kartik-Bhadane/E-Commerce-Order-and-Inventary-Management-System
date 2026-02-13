package com.example.E_CommerceOrder.service.impl;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.entity.Category;
import com.example.E_CommerceOrder.repository.CategoryRepo;
import com.example.E_CommerceOrder.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private  CategoryRepo categoryRepository;

    public CategoryServiceImpl(CategoryRepo categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}