package com.example.E_CommerceOrder.controller;

import org.springframework.web.bind.annotation.*;

import com.example.E_CommerceOrder.dto.CategoryRequestDto;
import com.example.E_CommerceOrder.entity.Category;
import com.example.E_CommerceOrder.service.CategoryService;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

  
    @PostMapping
    public Category addCategory(@RequestBody CategoryRequestDto dto) {
        return categoryService.addCategory(dto);
    }
}