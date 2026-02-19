package com.example.E_CommerceOrder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_CommerceOrder.entity.Cart;
import com.example.E_CommerceOrder.entity.User;

public interface CartRepo extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUser(User user);
}