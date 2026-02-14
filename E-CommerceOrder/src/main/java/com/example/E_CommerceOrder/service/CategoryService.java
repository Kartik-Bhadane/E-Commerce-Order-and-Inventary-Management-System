package com.example.E_CommerceOrder.service;

import com.example.E_CommerceOrder.dto.CategoryRequestDto;
import com.example.E_CommerceOrder.entity.Category;

public interface CategoryService {

    Category addCategory(CategoryRequestDto dto);
}