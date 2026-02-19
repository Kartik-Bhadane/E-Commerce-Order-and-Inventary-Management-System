package com.example.E_CommerceOrder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_CommerceOrder.entity.Cart;
import com.example.E_CommerceOrder.entity.CartItem;
import com.example.E_CommerceOrder.entity.Product;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}