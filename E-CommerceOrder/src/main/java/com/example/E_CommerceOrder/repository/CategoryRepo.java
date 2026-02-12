package com.example.E_CommerceOrder.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_CommerceOrder.entity.Category;



public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
