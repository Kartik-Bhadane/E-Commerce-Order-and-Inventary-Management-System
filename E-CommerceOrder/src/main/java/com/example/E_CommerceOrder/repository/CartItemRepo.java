package com.example.E_CommerceOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_CommerceOrder.entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
}