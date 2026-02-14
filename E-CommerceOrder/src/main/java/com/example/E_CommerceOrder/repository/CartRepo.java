package com.example.E_CommerceOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_CommerceOrder.entity.Cart;
import com.example.E_CommerceOrder.entity.User;

public interface CartRepo extends JpaRepository<Cart, Integer> {

    Cart findByUser(User user);
}