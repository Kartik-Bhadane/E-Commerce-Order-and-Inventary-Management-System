package com.example.E_CommerceOrder.controller;

import java.util.List;

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
    
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Integer id,
                                   @RequestBody CategoryRequestDto dto) {
        return categoryService.updateCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }

  
    @PostMapping("/add")
    public Category addCategory(@RequestBody CategoryRequestDto dto) {
        return categoryService.addCategory(dto);
    }
}