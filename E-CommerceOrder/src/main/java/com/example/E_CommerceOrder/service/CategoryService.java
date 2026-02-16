package com.example.E_CommerceOrder.service;

import java.util.List;

import com.example.E_CommerceOrder.dto.CategoryRequestDto;
import com.example.E_CommerceOrder.entity.Category;

public interface CategoryService {

    Category addCategory(CategoryRequestDto dto);
    
    List<Category> getAllCategories();

    Category updateCategory(Integer id, CategoryRequestDto dto);

    void deleteCategory(Integer id);
}