package com.example.E_CommerceOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_CommerceOrder.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

}
